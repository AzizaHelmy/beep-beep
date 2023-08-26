package presentation.meals

import cafe.adriel.voyager.core.model.coroutineScope
import domain.entity.Cuisine
import domain.usecase.IManageMealUseCase
import domain.usecase.IMangeCuisineUseCase

import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.inject
import presentation.base.BaseScreenModel

import presentation.meals.state.CuisineUIState
import presentation.meals.state.MealsScreenUIState
import presentation.meals.state.toUIState

class MealsScreenModel() :
    BaseScreenModel<MealsScreenUIState, MealsScreenUIEffect>(MealsScreenUIState()),
    MealScreenInteractionListener {
    override val viewModelScope: CoroutineScope
        get() = coroutineScope

    private val mangeCousin: IMangeCuisineUseCase by inject()
    private val mangeMeal: IManageMealUseCase by inject()


    init {
        getCuisine()
    }

    private fun getCuisine() {
        tryToExecute(
            function = { mangeCousin.getCuisineByResturantId(
                "1"
            ) },
            onSuccess = {value->
                updateState {
                    it.copy(cuisine = value.toUIState(), selectedCuisine =value.toUIState().first())
                }
                getMeals(state.value.selectedCuisine.id)
            },
            onError = { updateState { it.copy(error = it.error) } }
        )
    }


    private fun getMeals(cuisineId: String) {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            function = {
                if (cuisineId.isEmpty()) {
                    mangeMeal.getAllMeals()
                } else {
                    mangeCousin.getMealsByCuisineId(cuisineId)
                }
            },
            onSuccess = { value ->
                updateState { it.copy(meals = value.toUIState(), isLoading = false) }
            },
            onError = {
                updateState { it.copy(error = it.error, isLoading = false) }
            }
        )
    }

    override fun onClickBack() {
        sendNewEffect(MealsScreenUIEffect.Back)
    }

    override fun onClickMeal(mealId: String) {
        sendNewEffect(MealsScreenUIEffect.NavigateToMealDetails(mealId))
    }

    override fun onAddMeaClick() {
        sendNewEffect(MealsScreenUIEffect.NavigateToAddMeal)
    }

    override fun onClickCuisineType(type: CuisineUIState) {
        updateState { it.copy(selectedCuisine = type) }
        getMeals(type.id)
    }
}
