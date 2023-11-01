package org.thechance.common.presentation.restaurant

import org.thechance.common.presentation.base.BaseInteractionListener

interface RestaurantInteractionListener : BaseInteractionListener, AddCuisineInteractionListener,
    AddRestaurantInteractionListener, FilterRestaurantsInteractionListener {

    fun onSearchChange(restaurantName: String)

    fun onClickDropDownMenu()

    fun onDismissDropDownMenu()

    fun onPageClicked(pageNumber: Int)

    fun onItemPerPageChange(numberOfRestaurantsInPage: Int)

    fun onShowRestaurantMenu(restaurantId: String)

    fun onHideRestaurantMenu(restaurantId: String)

    fun onClickEditRestaurantMenuItem(restaurantId: String)

    fun onClickDeleteRestaurantMenuItem(id: String)

    fun onAddNewRestaurantClicked()

    fun onUpdateRestaurantClicked(restaurantId: String)

    fun onRetry()
}


interface AddCuisineInteractionListener {
  
    fun onClickAddCuisine()
    fun onClickImagePicker()
    fun onSelectedImage(image: Any?)

    fun onClickDeleteCuisine(cuisineId: String)

    fun onCloseAddCuisineDialog()

    fun onClickCreateCuisine()

    fun onChangeCuisineName(cuisineName: String)
}

interface AddRestaurantInteractionListener {

    fun onCancelCreateRestaurantClicked()

    fun onRestaurantNameChange(name: String)

    fun onOwnerUserNameChange(name: String)

    fun onPhoneNumberChange(number: String)

    fun onWorkingStartHourChange(hour: String)

    fun onWorkingEndHourChange(hour: String)

    fun onCreateNewRestaurantClicked()

    fun onLocationChange(location: String)
}

interface FilterRestaurantsInteractionListener {

    fun onClickFilterRatingBar(rating: Double)

    fun onClickFilterPriceBar(priceLevel: Int)

    fun onSaveFilterRestaurantsClicked(rating: Double, priceLevel: String)

    fun onCancelFilterRestaurantsClicked()

    fun onFilterClearAllClicked()
}