package presentation.home

import cafe.adriel.voyager.core.model.coroutineScope
import domain.entity.InProgressWrapper
import domain.entity.Restaurant
import domain.entity.User
import domain.usecase.IInProgressTrackerUseCase
import domain.usecase.IManageCartUseCase
import domain.usecase.IManageFavouriteUseCase
import domain.usecase.IManageOffersUseCase
import domain.usecase.IManageUserUseCase
import domain.usecase.IMangeRestaurantUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import presentation.base.BaseScreenModel
import presentation.base.ErrorState
import presentation.cuisines.CuisineUiState
import presentation.cuisines.toCuisineUiState

class HomeScreenModel(
    private val manageRestaurant: IMangeRestaurantUseCase,
    private val offers: IManageOffersUseCase,
    private val inProgressTrackerUseCase: IInProgressTrackerUseCase,
    private val manageUserUseCase: IManageUserUseCase,
    private val manageCart: IManageCartUseCase,
    private val manageFavorite: IManageFavouriteUseCase,
) : BaseScreenModel<HomeScreenUiState, HomeScreenUiEffect>(HomeScreenUiState()),
    HomeScreenInteractionListener {
    override val viewModelScope: CoroutineScope = coroutineScope

    init {
        getUserWallet()
        getInProgress()
        getRecommendedCuisines()
        getFavoriteRestaurants()
        getNewOffers()
        checkIfThereIsOrderInCart()
    }

    private fun checkIfThereIsOrderInCart() {
        tryToExecute(
            { manageCart.checkIfThereIsOrderInCart() },
            ::onCheckIfThereIsOrderInCartSuccess,
            ::onCheckIfThereIsOrderInCartError
        )
    }

    private fun onCheckIfThereIsOrderInCartSuccess(isEmpty: Boolean) {
        updateState { it.copy(showCart = !isEmpty) }
    }

    private fun onCheckIfThereIsOrderInCartError(errorState: ErrorState) {
        updateState { it.copy(showCart = false) }
    }

    private fun getUserWallet() {
        tryToExecute(
            { manageUserUseCase.getUserWallet() },
            ::onGetUserWalletSuccess,
            ::onGetUserWalletError
        )
    }

    private fun onGetUserWalletError(errorState: ErrorState) {
        updateState { it.copy(user = it.user.copy(isLogin = false)) }
    }

    private fun onGetUserWalletSuccess(user: User) {
        updateState { it.copy(user = user.toUIState()) }
    }

    private fun getInProgress() {
        tryToExecute(
            { inProgressTrackerUseCase.getInProgress() },
            ::onGetInProgressSuccess,
            ::onGetCuisinesError
        )
    }

    private fun onGetInProgressSuccess(inProgressWrapper: InProgressWrapper) {
        updateState { it.copy(inProgressWrapper = inProgressWrapper) }
    }

    override fun onClickCuisineItem(cuisineId: String) {
        sendNewEffect(HomeScreenUiEffect.NavigateToCuisineDetails(cuisineId))
    }

    override fun onclickSeeAllCuisines() {
        sendNewEffect(HomeScreenUiEffect.NavigateToCuisines)
    }

    override fun onClickChatSupport() {
        sendNewEffect(HomeScreenUiEffect.NavigateToChatSupport)
    }

    override fun onClickOrderTaxi() {
        sendNewEffect(HomeScreenUiEffect.NavigateToOrderTaxi)
    }

    override fun onClickOrderFood() {
        sendNewEffect(HomeScreenUiEffect.ScrollDownToRecommendedRestaurants)
    }

    override fun onClickOffersSlider(position: Int) {
        coroutineScope.launch {
            val id = offers.getNewOffers()[position].id
            sendNewEffect(HomeScreenUiEffect.NavigateToOfferItem(id))
        }
    }



    override fun onClickOrderAgain(orderId: String) {
        sendNewEffect(HomeScreenUiEffect.NavigateToOrderDetails(orderId))
    }

    override fun onLoginClicked() {
        sendNewEffect(HomeScreenUiEffect.NavigateLoginScreen)
    }

    override fun onClickCartCard() {
        sendNewEffect(HomeScreenUiEffect.NavigateToCart)
    }

    override fun onChangeSearchText(searchText: String) {
        updateState { it.copy(searchTerm = searchText) }
    }

    private fun getRecommendedCuisines() {
        tryToExecute(
            { manageRestaurant.getCuisines().toCuisineUiState() },
            ::onGetCuisinesSuccess,
            ::onGetCuisinesError
        )
    }

    private fun onGetCuisinesSuccess(cuisines: List<CuisineUiState>) {
        val popularCuisines = cuisines.shuffled().take(4)
        updateState { it.copy(recommendedCuisines = popularCuisines) }
    }

    private fun onGetCuisinesError(error: ErrorState) {
        println("error is $error")
    }

    private fun getFavoriteRestaurants() {
        tryToExecute(
            { manageFavorite.getFavoriteRestaurants() },
            ::onGetFavoriteRestaurantsSuccess,
            ::onGetFavoriteRestaurantsError
        )
    }

    private fun onGetFavoriteRestaurantsSuccess(restaurants: List<Restaurant>) {
        updateState { it.copy(favoriteRestaurants = restaurants.toRestaurantUiState()) }
    }

    private fun onGetFavoriteRestaurantsError(error: ErrorState) {
        println("error is $error")
    }


    private fun getNewOffers() {
        tryToExecute(
            { offers.getNewOffers().map { it.toUiState() } },
            ::onGetNewOffersSuccess,
            ::onGetNewOffersError
        )
    }

    private fun onGetNewOffersSuccess(offers: List<OfferUiState>) {
        updateState { it.copy(offers = offers) }
    }

    private fun onGetNewOffersError(error: ErrorState) {
        println("error is $error")
    }
}