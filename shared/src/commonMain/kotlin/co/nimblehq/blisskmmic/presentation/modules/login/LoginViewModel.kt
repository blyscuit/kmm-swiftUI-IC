package co.nimblehq.blisskmmic.presentation.modules.login

import co.nimblehq.blisskmmic.data.network.helpers.toErrorMessage
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCase
import co.nimblehq.blisskmmic.presentation.modules.ViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class LoginViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    constructor() : this(false, false, null)

    constructor(error: String?) : this(false, false, error)
}

class LoginViewModel(private val logInUseCase: LogInUseCase): ViewModel() {

    private val mutableViewState: MutableStateFlow<LoginViewState> =
        MutableStateFlow(LoginViewState())

    val viewState: StateFlow<LoginViewState> = mutableViewState

    fun logIn(email: String, password: String) {
        mutableViewState.update {
            LoginViewState(isLoading = true)
        }
        viewModelScope.launch {
            logInUseCase(email, password)
                .catch { error ->
                    mutableViewState.update { LoginViewState(isLoading = false, error = error.toErrorMessage()) }
                }
                .collect {
                    mutableViewState.update { LoginViewState(isSuccess = true, isLoading = false) }
                }
        }
    }
}
