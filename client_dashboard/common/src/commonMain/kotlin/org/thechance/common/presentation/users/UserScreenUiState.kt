package org.thechance.common.presentation.users

import org.thechance.common.domain.entity.DataWrapper
import org.thechance.common.domain.entity.Permission
import org.thechance.common.domain.entity.User
import org.thechance.common.presentation.composables.table.Header
import org.thechance.common.presentation.util.ErrorState

data class UserScreenUiState(
    val tableHeader: List<Header> = listOf(
        Header("No.", 1f),
        Header("Users", 3f),
        Header("Username", 3f),
        Header("Email", 3f),
        Header("Country", 3f),
        Header("Permission", 3f),
        Header("", 1f),
    ),
    val pageInfo: UserPageInfoUiState = UserPageInfoUiState(),
    val specifiedUsers: Int = 10,
    val currentPage: Int = 1,
    val search: String = "",
    val permissionsDialog: PermissionsDialogUiState = PermissionsDialogUiState(),
    val filter: FilterUiState = FilterUiState(),
    val allPermissions: List<PermissionUiState> = listOf(
        PermissionUiState.RESTAURANT_OWNER,
        PermissionUiState.TAXI_DRIVER,
        PermissionUiState.END_USER,
        PermissionUiState.SUPPORT,
        PermissionUiState.DELIVERY,
        PermissionUiState.DASHBOARD_ADMIN,
    ),
    val error: ErrorState = ErrorState.UnKnownError,
    val isLoading: Boolean = true,
    val userMenu: String = "",
) {
    data class UserPageInfoUiState(
        val data: List<UserUiState> = emptyList(),
        val numberOfUsers: Int = 0,
        val totalPages: Int = 0,
    )

    data class UserUiState(
        val fullName: String = "",
        val username: String = "",
        val email: String = "",
        val country: String = "",
        val permissions: List<PermissionUiState> = emptyList(),
    )

    enum class PermissionUiState(val iconPath: String) {
        RESTAURANT_OWNER("outline_restaurants.xml"),
        TAXI_DRIVER("ic_taxi.xml"),
        END_USER("ic_end_user.xml"),
        SUPPORT("ic_support.xml"),
        DELIVERY("ic_delivery.xml"),
        DASHBOARD_ADMIN("ic_admin.xml"),
    }

    data class PermissionsDialogUiState(
        val show: Boolean = false,
        val username: String = "",
        val permissions: List<PermissionUiState> = emptyList(),
    )

    data class FilterUiState(
        val show: Boolean = false,
        val permissions: List<PermissionUiState> = emptyList(),
        val countries: List<CountryUiState> = listOf(
            CountryUiState(
                name = "Iraq",
                selected = false,
            ),
            CountryUiState(
                name = "Palestine",
                selected = false,
            ),
            CountryUiState(
                name = "Jordan",
                selected = false,
            ),
            CountryUiState(
                name = "Syria",
                selected = false,
            ),
            CountryUiState(
                name = "Egypt",
                selected = false,
            ),
        ),
    )

    data class CountryUiState(
        val name: String = "",
        val selected: Boolean = false
    )
}

fun List<User>.toUiState(): List<UserScreenUiState.UserUiState> {
    return map {
        UserScreenUiState.UserUiState(
            fullName = it.fullName,
            username = it.username,
            email = it.email,
            country = it.country,
            permissions = it.permission.map { permission ->
                when (permission) {
                    Permission.RESTAURANT -> UserScreenUiState.PermissionUiState.RESTAURANT_OWNER
                    Permission.DRIVER -> UserScreenUiState.PermissionUiState.TAXI_DRIVER
                    Permission.END_USER -> UserScreenUiState.PermissionUiState.END_USER
                    Permission.SUPPORT -> UserScreenUiState.PermissionUiState.SUPPORT
                    Permission.DELIVERY -> UserScreenUiState.PermissionUiState.DELIVERY
                    Permission.ADMIN -> UserScreenUiState.PermissionUiState.DASHBOARD_ADMIN
                }
            },
        )
    }
}

fun DataWrapper<User>.toUiState(): UserScreenUiState.UserPageInfoUiState {
    return UserScreenUiState.UserPageInfoUiState(
        data = result.toUiState(),
        totalPages = totalPages,
        numberOfUsers = numberOfResult
    )
}

fun UserScreenUiState.PermissionUiState.toEntity(): Permission {
    return when (this) {
        UserScreenUiState.PermissionUiState.END_USER -> Permission.END_USER
        UserScreenUiState.PermissionUiState.DASHBOARD_ADMIN -> Permission.ADMIN
        UserScreenUiState.PermissionUiState.SUPPORT -> Permission.SUPPORT
        UserScreenUiState.PermissionUiState.DELIVERY -> Permission.DELIVERY
        UserScreenUiState.PermissionUiState.RESTAURANT_OWNER -> Permission.RESTAURANT
        UserScreenUiState.PermissionUiState.TAXI_DRIVER -> Permission.DRIVER
    }
}

fun List<UserScreenUiState.PermissionUiState>.toEntity() = this.map { it.toEntity() }