package org.thechance.api_gateway.endpoints

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.thechance.api_gateway.data.service.RestaurantService
import org.thechance.api_gateway.endpoints.utils.authenticateWithRole
import org.thechance.api_gateway.endpoints.utils.extractLocalizationHeader
import org.thechance.api_gateway.endpoints.utils.respondWithResult
import org.thechance.api_gateway.util.Role
import java.util.*


fun Route.cuisineRoute() {

    val restaurantService: RestaurantService by inject()

    route("/cuisine") {
        authenticateWithRole(Role.DASHBOARD_ADMIN) {
            post {
                val params = call.receiveParameters()
                val name = params["name"]?.trim().toString()
                val (language, countryCode) = extractLocalizationHeader()
                val cuisine = restaurantService.addCuisine(name, Locale(language, countryCode))
                respondWithResult(HttpStatusCode.Created, cuisine)
            }
        }

        get("/{id}/meals") {
            val (language, countryCode) = extractLocalizationHeader()
            val cuisineId = call.parameters["id"]?.trim().toString()
            val meals = restaurantService.getMealsByCuisineId(
                cuisineId = cuisineId,
                locale = Locale(language, countryCode)
            )
            respondWithResult(HttpStatusCode.OK, meals)
        }

    }

    get("/cuisines") {
        val (language, countryCode) = extractLocalizationHeader()
        val cuisines = restaurantService.getCuisines(locale = Locale(language, countryCode))
        respondWithResult(HttpStatusCode.OK, cuisines)
    }
}

