package org.thechance.api_gateway.endpoints


import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import org.koin.ktor.ext.inject
import org.thechance.api_gateway.data.gateway.IdentityGateway
import org.thechance.api_gateway.data.model.restaurant.Restaurant
import org.thechance.api_gateway.endpoints.gateway.IRestaurantGateway
import org.thechance.api_gateway.endpoints.utils.*
import org.thechance.api_gateway.util.Claim.USER_ID
import org.thechance.api_gateway.util.Role
import java.util.*

fun Route.restaurantRoutes() {

    val restaurantGateway: IRestaurantGateway by inject()
    val identityGateway: IdentityGateway by inject()
    val webSocketServerHandler: WebSocketServerHandler by inject()

    route("/restaurants") {
        get {
            val (language, countryCode) = extractLocalizationHeader()
            val page = call.parameters["page"]?.toInt() ?: 1
            val limit = call.parameters["limit"]?.toInt() ?: 20
            val restaurants = restaurantGateway.getRestaurants(page, limit, locale = Locale(language, countryCode))
            respondWithResult(HttpStatusCode.OK, restaurants)
        }

        authenticateWithRole(Role.RESTAURANT_OWNER) {
            get("/mine") {
                val tokenClaim = call.principal<JWTPrincipal>()
                val ownerId = tokenClaim?.get(USER_ID).toString()
                val (language, countryCode) = extractLocalizationHeader()
                val result = restaurantGateway.getRestaurantsByOwnerId(
                    ownerId = ownerId, locale = Locale(language, countryCode)
                )
                respondWithResult(HttpStatusCode.OK, result)
            }
        }
    }

    route("/restaurant") {

        get("/{restaurantId}/meals") {
            val (language, countryCode) = extractLocalizationHeader()
            val page = call.parameters["page"]?.toInt() ?: 1
            val limit = call.parameters["limit"]?.toInt() ?: 20
            val restaurantId = call.parameters["restaurantId"]?.trim().toString()
            val meals = restaurantGateway.getMealsByRestaurantId(
                restaurantId = restaurantId,
                page = page,
                limit = limit,
                locale = Locale(language, countryCode)
            )
            respondWithResult(HttpStatusCode.OK, meals)
        }

        get("/{restaurantId}") {
            val (language, countryCode) = extractLocalizationHeader()
            val restaurantId = call.parameters["restaurantId"]?.trim().toString()
            val restaurant = restaurantGateway.getRestaurantInfo(
                locale = Locale(language, countryCode), restaurantId = restaurantId
            )
            respondWithResult(HttpStatusCode.OK, restaurant)
        }

        authenticateWithRole(Role.DASHBOARD_ADMIN) {
            post {
                val (language, countryCode) = extractLocalizationHeader()
                val restaurant = call.receive<Restaurant>()
                val user =
                    identityGateway.updateUserPermission(restaurant.ownerId ?: "", addPermission(Role.RESTAURANT_OWNER))
                val newRestaurant =
                    restaurantGateway.addRestaurant(
                        restaurant = restaurant.copy(ownerId = user.id),
                        Locale(language, countryCode)
                    )
                respondWithResult(HttpStatusCode.Created, newRestaurant)
            }
        }


        authenticateWithRole(Role.DASHBOARD_ADMIN) {
            put {
                val (language, countryCode) = extractLocalizationHeader()
                val restaurant = call.receive<Restaurant>()
                val updatedRestaurant = restaurantGateway.updateRestaurant(
                    restaurant, isAdmin = true, Locale(language, countryCode)
                )
                respondWithResult(HttpStatusCode.OK, updatedRestaurant)
            }
        }

        authenticateWithRole(Role.RESTAURANT_OWNER) {
            put("/details") {
                val (language, countryCode) = extractLocalizationHeader()
                val restaurant = call.receive<Restaurant>()
                val tokenClaim = call.principal<JWTPrincipal>()
                val ownerId = tokenClaim?.get(USER_ID).toString()
                val updatedRestaurant = restaurantGateway.updateRestaurant(
                    locale = Locale(language, countryCode),
                    isAdmin = false,
                    restaurant = restaurant.copy(ownerId =ownerId )
                )
                respondWithResult(HttpStatusCode.OK, updatedRestaurant)
            }
        }

        authenticateWithRole(Role.RESTAURANT_OWNER) {

            route("/orders") {
                webSocket("/{restaurantId}") {
                    val restaurantId = call.parameters["restaurantId"]?.trim().orEmpty()
                    val (language, countryCode) = extractLocalizationHeaderFromWebSocket()
                    val orders = restaurantGateway.restaurantOrders(restaurantId, Locale(language, countryCode))
                    webSocketServerHandler.sessions[restaurantId] = this
                    webSocketServerHandler.sessions[restaurantId]?.let {
                        webSocketServerHandler.tryToCollectFormWebSocket(
                            orders,
                            it
                        )
                    }
                }

                get("/{restaurantId}") {
                    val restaurantId = call.parameters["restaurantId"]?.trim().orEmpty()
                    val (language, countryCode) = extractLocalizationHeader()
                    val result = restaurantGateway.getActiveOrders(restaurantId, Locale(language, countryCode))
                    respondWithResult(HttpStatusCode.OK, result)
                }


                get("/history/{id}") {
                    val id = call.parameters["id"]?.trim().toString()
                    val page = call.parameters["page"]?.trim()?.toInt() ?: 1
                    val limit = call.parameters["limit"]?.trim()?.toInt() ?: 10
                    val (language, countryCode) = extractLocalizationHeader()
                    val result = restaurantGateway.getOrdersHistory(
                        restaurantId = id,
                        page = page,
                        limit = limit,
                        locale = Locale(language, countryCode)
                    )
                    respondWithResult(HttpStatusCode.OK, result)
                }

                get("/count-by-days-back") {
                    val id = call.parameters["restaurantId"]?.trim().toString()
                    val daysBack = call.parameters["daysBack"]?.trim()?.toInt() ?: 7
                    val (language, countryCode) = extractLocalizationHeader()
                    val result = restaurantGateway.getOrdersCountByDaysBefore(
                        restaurantId = id,
                        daysBack = daysBack,
                        locale = Locale(language, countryCode)
                    )
                    respondWithResult(HttpStatusCode.OK, result)
                }

                get("/revenue-by-days-back") {
                    val id = call.parameters["restaurantId"]?.trim().toString()
                    val daysBack = call.parameters["daysBack"]?.trim()?.toInt() ?: 7
                    val (language, countryCode) = extractLocalizationHeader()
                    val result = restaurantGateway.getOrdersRevenueByDaysBefore(
                        restaurantId = id,
                        daysBack = daysBack,
                        locale = Locale(language, countryCode)
                    )
                    respondWithResult(HttpStatusCode.OK, result)
                }

                put("/{id}/status") {
                    val id = call.parameters["id"]?.trim().toString()
                    val params = call.receiveParameters()
                    val status = params["status"]?.trim()?.toInt() ?: 0
                    val (language, countryCode) = extractLocalizationHeader()
                    val result = restaurantGateway.updateOrderStatus(id, status, Locale(language, countryCode))
                    respondWithResult(HttpStatusCode.OK, result)
                }

                delete("/{restaurantId}") {
                    val restaurantId = call.parameters["restaurantId"]?.trim().toString()
                    val (language, countryCode) = extractLocalizationHeader()
                    val result = restaurantGateway.deleteRestaurant(restaurantId, Locale(language, countryCode))
                    respondWithResult(HttpStatusCode.OK, result)
                }
            }
        }
    }
}