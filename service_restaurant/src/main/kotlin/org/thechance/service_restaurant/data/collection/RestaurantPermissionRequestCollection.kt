package org.thechance.service_restaurant.data.collection

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class RestaurantPermissionRequestCollection(
    val restaurantName: String,
    val ownerEmail: String,
    val cause: String,
){
    @Contextual
    @SerialName("_id")
    val id: UUID = UUID.randomUUID()
    val isDeleted: Boolean = false
}
