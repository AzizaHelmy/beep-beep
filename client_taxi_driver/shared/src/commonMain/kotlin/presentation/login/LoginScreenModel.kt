package presentation.login

import cafe.adriel.voyager.core.model.coroutineScope
import domain.usecase.ILoginUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import presentation.base.BaseScreenModel
import presentation.base.ErrorState

class LoginScreenModel(private val loginUserUseCase: ILoginUserUseCase) :
    BaseScreenModel<LoginScreenUIState, LoginScreenUIEffect>(LoginScreenUIState()),
    LoginScreenInteractionListener {
    init {
        coroutineScope.launch {
            if (loginUserUseCase.getKeepMeLoggedInFlag()) {
                sendNewEffect(LoginScreenUIEffect.LoginEffect(""))
            }
        }
    }

    override fun onUsernameChanged(username: String) {
        updateState { it.copy(username = username) }
    }

    override fun onPasswordChanged(password: String) {
        updateState { it.copy(password = password) }
    }

    override fun onKeepLoggedInClicked() {
        updateState { it.copy(keepLoggedIn = !it.keepLoggedIn) }
    }

    override fun onClickLogin(
        userName: String,
        password: String,
        isKeepMeLoggedInChecked: Boolean
    ) {
        tryToExecute(
            { loginUserUseCase.loginUser(userName, password, isKeepMeLoggedInChecked) },
            { onLoginSuccess() },
            ::onLoginFailed
        )
    }

    private fun onLoginSuccess() {
        updateState {
            it.copy(
                isLoading = false,
                isSuccess = true,
                usernameErrorMsg = "",
                passwordErrorMsg = ""
            )
        }

        sendNewEffect(LoginScreenUIEffect.LoginEffect(""))
    }

    private fun onLoginFailed(error: ErrorState) {
        updateState { it.copy(isLoading = false, error = error) }
        handleErrorState(error)
    }

    private fun handleErrorState(error: ErrorState) {
        when (error) {
            ErrorState.NoInternet -> {/* TODO: */
            }

            ErrorState.UnAuthorized -> {/* TODO: */
            }

            ErrorState.HasNoPermission -> state.value.bottomSheetUiState.sheetState.show()


            is ErrorState.UnknownError -> {/* TODO: */
            }

            is ErrorState.ServerError -> {/* TODO: */
            }

            is ErrorState.InvalidCredentials -> {
                updateState {
                    it.copy(
                        passwordErrorMsg = error.errorMessage,
                        isPasswordError = true,
                    )
                }
            }

            is ErrorState.NotFound -> {
                updateState {
                    it.copy(
                        usernameErrorMsg = error.errorMessage,
                        isUsernameError = true
                    )
                }
            }

            else -> {}
        }
    }

    // region permission
    override fun onDriverFullNameChanged(driverFullName: String) {
        updateState { it.copy(bottomSheetUiState = it.bottomSheetUiState.copy(driverFullName = driverFullName)) }
    }

    override fun onOwnerEmailChanged(ownerEmail: String) {
        updateState { it.copy(bottomSheetUiState = it.bottomSheetUiState.copy(driverEmail = ownerEmail)) }
    }

    override fun onDescriptionChanged(description: String) {
        updateState { it.copy(bottomSheetUiState = it.bottomSheetUiState.copy(description = description)) }
    }

    override fun onRequestPermissionClicked() {
        coroutineScope.launch {
            state.value.bottomSheetUiState.sheetState.dismiss()
            delayAndChangePermissionSheetState(true)
            state.value.bottomSheetUiState.sheetState.show()
        }
    }


    override fun onSubmitClicked(
        driverFullName: String,
        driverEmail: String,
        description: String
    ) {
        tryToExecute(
            {
                loginUserUseCase.requestPermission(
                    driverFullName,
                    driverEmail,
                    description
                )
            },
            { onAskForPermissionSuccess() },
            ::onAskForPermissionFailed
        )
    }

    private fun onAskForPermissionSuccess() {
        onDismissSheet()
        // todo send effect for that to show toast or something
    }


    private fun onAskForPermissionFailed(error: ErrorState) {
        // todo send effect for that to show toast or something
    }

    override fun onDismissSheet() {
        state.value.bottomSheetUiState.sheetState.dismiss()
        coroutineScope.launch {
            delayAndChangePermissionSheetState(false)
        }
    }

    private suspend fun delayAndChangePermissionSheetState(show: Boolean) {
        delay(300)
        updateState { it.copy(bottomSheetUiState = it.bottomSheetUiState.copy(showPermissionSheet = show)) }
    }
    // endregion
}
