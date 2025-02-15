package presentation.meals

import presentation.base.BaseInteractionListener

interface MealScreenInteractionListener : BaseInteractionListener {
    fun onClickBack()

    fun onClickMeal(mealId: String)

    fun onClickCuisineType(type: CuisineUIState)

    fun onAddMeaClick()
}
