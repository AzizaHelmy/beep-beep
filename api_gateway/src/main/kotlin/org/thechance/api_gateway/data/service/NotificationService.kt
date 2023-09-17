package org.thechance.api_gateway.data.service

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.Attributes
import org.koin.core.annotation.Single
import org.thechance.api_gateway.data.model.UserDto
import org.thechance.api_gateway.data.utils.ErrorHandler
import org.thechance.api_gateway.data.utils.tryToExecute
import org.thechance.api_gateway.util.APIs

@Single
class NotificationService(
    private val client: HttpClient,
    private val attributes: Attributes,
    private val errorHandler: ErrorHandler
) {

    suspend fun getUserToken(id: String,languageCode: String) {
        client.tryToExecute<List<String>>(
            APIs.NOTIFICATION_API, attributes = attributes, setErrorMessage = { errorCodes ->
                errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
            }
        ) {
            get("tokens/user/$id")
        }

    }
    suspend fun getAllUsersTokens(ids: List<String>,languageCode: String) {
        client.tryToExecute<List<String>>(
            APIs.NOTIFICATION_API, attributes = attributes, setErrorMessage = { errorCodes ->
                errorHandler.getLocalizedErrorMessage(errorCodes, languageCode)
            }
        ) {
            get("tokens/users") {
                parameter("ids", ids)
            }
        }
    }
}