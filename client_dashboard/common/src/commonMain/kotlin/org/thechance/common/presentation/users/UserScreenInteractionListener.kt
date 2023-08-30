package org.thechance.common.presentation.users

import org.thechance.common.presentation.base.BaseInteractionListener

interface UserScreenInteractionListener : BaseInteractionListener, FilterMenuListener,
    EditUserMenuListener, PageListener {
    fun onDeleteUserMenu(user: UserScreenUiState.UserUiState)
    fun onSearchInputChange(text: String)
}

interface EditUserMenuListener {
    fun showEditUserMenu(username: String)
    fun hideEditUserMenu()
    fun onEditUserMenuItemClicked(user: UserScreenUiState.UserUiState)
    fun onSaveEditUserMenu()
    fun onCancelEditUserMenu()
    fun onEditUserMenuPermissionClick(permission: UserScreenUiState.PermissionUiState)
}

interface FilterMenuListener {
    fun showFilterMenu()
    fun hideFilterMenu()
    fun onFilterMenuPermissionClick(permission: UserScreenUiState.PermissionUiState)
    fun onFilterMenuCountryClick(country: UserScreenUiState.CountryUiState)
    fun onFilterSaved()
}

interface PageListener {
    fun onItemsIndicatorChange(itemPerPage: Int)
    fun onPageClick(pageNumber: Int)
}