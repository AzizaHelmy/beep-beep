package org.thechance.service_location.util

import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flowOn
import org.thechance.service_location.data.model.WebSocketLocation
import org.thechance.service_location.data.utils.MultiErrorException
import java.util.concurrent.ConcurrentHashMap

class SocketHandler {
    val location: ConcurrentHashMap<String,WebSocketLocation> = ConcurrentHashMap()

    suspend fun broadcastLocation(locationId: String) {

        val ownerSession = location[locationId]?.ownerSession
        val locations = location[locationId]?.locations


        try {
            locations
                ?.drop(1)
                ?.flowOn(Dispatchers.IO)
                ?.collect { location -> ownerSession?.sendSerialized(location) }
        } catch (e: MultiErrorException) {
            ownerSession?.send(e.errorCodes.toString())
            ownerSession?.close()
        } finally {
            this.location.remove(locationId)
        }
    }

}