package presentation.home

import domain.entity.Cuisine
import domain.entity.Offer

data class HomeScreenUiState(
    val offers: List<OfferUiState> = emptyList(),
    val recommendedCuisines: List<CuisineUiState> = emptyList(),
) {
    fun getOfferImages(): List<String> {
        return this.offers.map { it.image }
    }
}

data class CuisineUiState(
    val cuisineId: String = "",
    val cuisineName: String = "",
    val cuisineImageUrl: String = ""
)

data class OfferUiState(
    val id: String,
    val image: String
)

fun Offer.toUiState(): OfferUiState {
    return OfferUiState(
        id = id,
        image = image
    )
}

fun Cuisine.toCuisineUiState(): CuisineUiState {
    return CuisineUiState(
        cuisineId = cuisineId,
        cuisineName = cuisineName,
        cuisineImageUrl = cuisineImageUrl,
    )
}

fun List<Cuisine>.toCuisineUiState() = map { it.toCuisineUiState() }