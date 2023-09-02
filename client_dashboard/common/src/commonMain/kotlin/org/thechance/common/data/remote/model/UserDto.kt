package org.thechance.common.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.thechance.common.domain.entity.User

@Serializable
data class UserDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("full_name")
    val fullName: String? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("permissions")
    val permissions: List<PermissionDto?>? = null,
    @SerialName("image_url")
    val imageUrl: String,

    ) {
    @Serializable
    data class PermissionDto(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("permission")
        val permission: String? = null
    )
}

fun UserDto.toEntity() = User(
    id = id ?: "",
    fullName = fullName ?: "",
    country = country ?: "",
    username = username ?: "",
    email = email ?: "",
    permission = permissions?.map { enumValueOf(it?.permission ?: "") } ?: emptyList(),
    imageUrl = imageUrl
)

fun List<UserDto>.toEntity() = this.map { user ->
    User(
        id = user.id ?: "",
        fullName = user.fullName ?: "",
        country = user.country ?: "",
        username = user.username ?: "",
        email = user.email ?: "",
        permission = user.permissions?.map { enumValueOf(it?.permission ?: "") } ?: emptyList(),
        imageUrl = user.imageUrl
    )
}
