package data.remote.model

import domain.entity.Address

data class AddressDto(
    val latitude: Double? = null,
    val longitude: Double? = null,
)

fun AddressDto.toEntity(): Address {
    return Address(latitude = latitude, longitude = longitude)
}