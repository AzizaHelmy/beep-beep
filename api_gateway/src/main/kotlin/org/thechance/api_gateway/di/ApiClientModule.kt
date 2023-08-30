package org.thechance.api_gateway.di

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.thechance.api_gateway.util.APIs
import kotlin.time.Duration.Companion.seconds

@Module
class ApiClientModule {

    @Single
    fun provideHttpClientAttribute(): Attributes {
        return Attributes(true)
    }

    @Single
    fun provideHttpClient(
        clientAttributes: Attributes
    ): HttpClient {
        return HttpClient(OkHttp) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(Json)
                pingInterval = 20.seconds.inWholeMilliseconds
            }

            defaultRequest {
                header("Content-Type", "application/json")
                when (clientAttributes[AttributeKey<String>("API")]) {
                    APIs.IDENTITY_API.value -> {
                        url("http://127.0.0.1:8082")
//                        url("http://0.0.0.0:8082")
                    }

                    APIs.RESTAURANT_API.value -> {
                        url("http://127.0.0.1:8080")
//                        url("http://0.0.0.0:8083")
                    }

                    APIs.TAXI_API.value -> {
                        url("http://127.0.0.3:8080")
                    }

                    APIs.NOTIFICATION_API.value -> {
                        url("http://127.0.0.4:8080")
                    }
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}