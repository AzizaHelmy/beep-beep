package org.thechance.service_restaurant.data.collection

import kotlinx.serialization.Serializable

@Serializable
data class AddressCollection(
    val latitude: Double,
    val longitude: Double,
)

