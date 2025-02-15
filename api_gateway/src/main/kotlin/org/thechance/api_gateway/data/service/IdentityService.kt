package org.thechance.api_gateway.data.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single
import org.thechance.api_gateway.data.model.*
import org.thechance.api_gateway.data.model.authenticate.TokenConfiguration
import org.thechance.api_gateway.data.model.authenticate.TokenType
import org.thechance.api_gateway.data.model.identity.UserDetailsDto
import org.thechance.api_gateway.data.model.identity.UserDto
import org.thechance.api_gateway.data.model.identity.UserOptions
import org.thechance.api_gateway.data.model.identity.UserRegistrationDto
import org.thechance.api_gateway.data.model.restaurant.MealDto
import org.thechance.api_gateway.data.utils.ErrorHandler
import org.thechance.api_gateway.data.utils.tryToExecute
import org.thechance.api_gateway.util.APIs
import org.thechance.api_gateway.util.Claim.PERMISSION
import org.thechance.api_gateway.util.Claim.TOKEN_TYPE
import org.thechance.api_gateway.util.Claim.USERNAME
import org.thechance.api_gateway.util.Claim.USER_ID
import java.util.*

@Single
class IdentityService(
    private val client: HttpClient,
    private val attributes: Attributes,
    private val errorHandler: ErrorHandler
) {
    @OptIn(InternalAPI::class)
    suspend fun createUser(newUser: UserRegistrationDto, languageCode: String): UserDetailsDto {
        return client.tryToExecute<UserDetailsDto>(
            APIs.IDENTITY_API,
            attributes = attributes,
            setErrorMessage = { errorCodes ->
                errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
            }
        ) {
            post("/user") {
                body = Json.encodeToString(UserRegistrationDto.serializer(), newUser)
            }
        }
    }

    suspend fun loginUser(
        userName: String,
        password: String,
        tokenConfiguration: TokenConfiguration,
        languageCode: String,
        applicationId: String
    ): UserTokensResponse {
        client.tryToExecute<Boolean>(
            api = APIs.IDENTITY_API,
            attributes = attributes,
            setErrorMessage = { errorCodes ->
                errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
            }
        ) {
            post("/user/login") {
                headers.append("Application-Id", applicationId)
                formData {
                    parameter("username", userName)
                    parameter("password", password)
                }
            }
        }
        val user = getUserByUsername(username = userName, languageCode)
        return generateUserTokens(user.id, userName, user.permission, tokenConfiguration)
    }

    @OptIn(InternalAPI::class)
    suspend fun getUsers(
        options: UserOptions, languageCode: String
    ) = client.tryToExecute<PaginationResponse<UserDto>>(
        APIs.IDENTITY_API, attributes = attributes, setErrorMessage = { errorCodes ->
            errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
        }
    ) {
        post("/dashboard/user") {
            body = Json.encodeToString(UserOptions.serializer(), options)
        }
    }

    suspend fun getLastRegisteredUsers(limit: Int) = client.tryToExecute<List<UserDto>>(
        APIs.IDENTITY_API, attributes = attributes,
    ) {
        get("/dashboard/user/last-register") {
            parameter("limit", limit)
        }
    }

    suspend fun getUserAddresses(userId: String, languageCode: String): List<AddressDto> =
        client.tryToExecute<List<AddressDto>>(
            APIs.IDENTITY_API,
            attributes = attributes,
            setErrorMessage = { errorCodes -> errorHandler.getLocalizedErrorMessage(errorCodes, languageCode) },
            method = { get("/user/$userId/address") }
        )

    suspend fun getUserById(id: String, languageCode: String): UserDetailsDto =
        client.tryToExecute<UserDetailsDto>(
            APIs.IDENTITY_API,
            attributes = attributes,
            setErrorMessage = { errorCodes -> errorHandler.getLocalizedErrorMessage(errorCodes, languageCode) },
            method = { get("user/$id") }
        )

    @OptIn(InternalAPI::class)
    suspend fun updateUserProfile(id: String, fullName: String?, phone: String?, languageCode: String): UserDetailsDto {
        return client.tryToExecute(
            APIs.IDENTITY_API, attributes = attributes, setErrorMessage = { errorCodes ->
                errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
            }
        ) {
            val formData = FormDataContent(Parameters.build {
                fullName?.let { append("fullName", it) }
                phone?.let { append("phone", it) }
            })
            put("/user/$id") {
                body = formData
            }
        }
    }

    suspend fun getUserByUsername(username: String?, languageCode: String): UserDto = client.tryToExecute<UserDto>(
        APIs.IDENTITY_API, attributes = attributes, setErrorMessage = { errorCodes ->
            errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
        }
    ) {
        get("user/get-user") {
            parameter("username", username)
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun updateUserPermission(userId: String, permission: List<Int>, languageCode: String): UserDto {
        return client.tryToExecute<UserDto>(
            APIs.IDENTITY_API, attributes = attributes,
            setErrorMessage = { errorCodes ->
                errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
            }
        ) {
            put("/dashboard/user/$userId/permission") {
                body = Json.encodeToString(ListSerializer(Int.serializer()), permission)
            }
        }
    }

    suspend fun deleteUser(userId: String, languageCode: String): Boolean {
        return client.tryToExecute<Boolean>(
            api = APIs.IDENTITY_API,
            attributes = attributes,
            setErrorMessage = { errorCodes ->
                errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
            }) {
            delete("/user/$userId")
        }
    }

    suspend fun getFavoriteRestaurantsIds(userId: String, languageCode: String) = client.tryToExecute<List<String>>(
        api = APIs.IDENTITY_API,
        attributes = attributes,
        setErrorMessage = { errorCodes ->
            errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
        }) {
        get("/user/$userId/favorite")
    }

    suspend fun addRestaurantToFavorite(userId: String, restaurantId: String, languageCode: String) =
        client.tryToExecute<Boolean>(
            api = APIs.IDENTITY_API,
            attributes = attributes,
            setErrorMessage = { errorCodes ->
                errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
            }) {
            post("/user/$userId/favorite") {
                formData {
                    parameter("restaurantId", restaurantId)
                }
            }
        }

    suspend fun deleteRestaurantFromFavorite(userId: String, restaurantId: String, languageCode: String) =
        client.tryToExecute<Boolean>(
            api = APIs.IDENTITY_API,
            attributes = attributes,
            setErrorMessage = { errorCodes ->
                errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
            }) {
            delete("/user/$userId/favorite") {
                formData {
                    parameter("restaurantId", restaurantId)
                }
            }
        }

    fun generateUserTokens(
        userId: String, username: String, userPermission: Int, tokenConfiguration: TokenConfiguration
    ): UserTokensResponse {

        val accessTokenExpirationDate = getExpirationDate(tokenConfiguration.accessTokenExpirationTimestamp)
        val refreshTokenExpirationDate = getExpirationDate(tokenConfiguration.refreshTokenExpirationTimestamp)

        val refreshToken =
            generateToken(userId, username, userPermission, tokenConfiguration, TokenType.REFRESH_TOKEN)
        val accessToken =
            generateToken(userId, username, userPermission, tokenConfiguration, TokenType.ACCESS_TOKEN)

        return UserTokensResponse(
            accessTokenExpirationDate.time,
            refreshTokenExpirationDate.time,
            accessToken,
            refreshToken
        )
    }

    private fun getExpirationDate(timestamp: Long): Date {
        return Date(System.currentTimeMillis() + timestamp)
    }

    private fun generateToken(
        userId: String,
        username: String,
        userPermission: Int,
        tokenConfiguration: TokenConfiguration,
        tokenType: TokenType
    ): String {
        val accessToken = JWT.create()
            .withIssuer(tokenConfiguration.issuer)
            .withAudience(tokenConfiguration.audience)
            .withExpiresAt(Date(System.currentTimeMillis() + tokenConfiguration.accessTokenExpirationTimestamp))
            .withClaim(USER_ID, userId)
            .withClaim(PERMISSION, userPermission.toString())
            .withClaim(USERNAME, username)
            .withClaim(TOKEN_TYPE, tokenType.name)

        return accessToken.sign(Algorithm.HMAC256(tokenConfiguration.secret))
    }

    @OptIn(InternalAPI::class)
    suspend fun updateUserLocation(userId: String, location: LocationDto, language: String) =
        client.tryToExecute<AddressDto>(
            api = APIs.IDENTITY_API,
            attributes = attributes,
            setErrorMessage = { errorCodes ->
                errorHandler.getLocalizedErrorMessage(errorCodes, language)
            }) {
            post("/user/$userId/address/location") {
                body = Json.encodeToString(LocationDto.serializer(), location)
            }
        }

    suspend fun isUserExistedInDb(userId: String?, languageCode: String): Boolean = client.tryToExecute<Boolean>(
        APIs.IDENTITY_API,
        attributes = attributes,
        setErrorMessage = { errorCodes -> errorHandler.getLocalizedErrorMessage(errorCodes, languageCode) },
        method = { get("user/isExisted/$userId") }
    )

}
