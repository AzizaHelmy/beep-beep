package org.thechance.service_identity.data.collection.mappers

import org.thechance.service_identity.data.collection.DetailedUserCollection
import org.thechance.service_identity.domain.entity.Address
import org.thechance.service_identity.domain.entity.User

fun DetailedUserCollection.toEntity(
    walletBalance: Double, currency: String, addresses: List<Address>, country: String, permission: Int
) = User(
    id = id.toString(),
    fullName = fullName,
    username = username,
    email = email,
    walletBalance = walletBalance,
    addresses = addresses,
    country = country,
    permission = permission,
    currency = currency
)


fun List<DetailedUserCollection>.toEntity(
    walletBalance: Double, currency: String, addresses: List<Address>, country: String, permission: Int
) = map {
    it.toEntity(
        walletBalance = walletBalance,
        currency = currency,
        addresses = addresses,
        country = country,
        permission = permission
    )
}