package co.nimblehq.blisskmmic.presentation.modules.login

import co.nimblehq.blisskmmic.data.network.helpers.toErrorMessage
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCase
import co.nimblehq.blisskmmic.presentation.modules.BaseViewModel
import co.nimblehq.blisskmmic.helpers.extensions.isValidEmail
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class LoginViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isEmailError: Boolean = false,
    var isPasswordError: Boolean = false
) {
    constructor() : this(false)
    constructor(error: String?) : this(false, false, error)
}

class LoginViewModel(private val logInUseCase: LogInUseCase): BaseViewModel() {

    private val mutableViewState: MutableStateFlow<LoginViewState> =
        MutableStateFlow(LoginViewState())

    val viewState: StateFlow<LoginViewState> = mutableViewState

    fun login(email: String, password: String) {
        if (!validInput(email, password)) return
        setStateLoading()
        viewModelScope.launch {
            logInUseCase(email, password)
                .catch { error -> handleLoginError(error) }
                .collect { loginSuccess() }
        }
    }

    private fun validInput(email: String, password: String): Boolean {
        val emailError = !email.isValidEmail()
        val passwordError = password.isEmpty()
        if (emailError or passwordError)  {
            mutableViewState.update { LoginViewState(isEmailError = emailError, isPasswordError = passwordError) }
            return false
        }
        return true
    }

    private fun setStateLoading() {
        mutableViewState.update {
            LoginViewState(isLoading = true)
        }
    }

    private fun handleLoginError(error: Throwable) {
        mutableViewState.update { LoginViewState(isLoading = false, error = error.toErrorMessage()) }
    }

    private fun loginSuccess() {
        mutableViewState.update { LoginViewState(isSuccess = true, isLoading = false) }
    }
}
