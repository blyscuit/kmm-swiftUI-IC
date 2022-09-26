package co.nimblehq.blisskmmic.presentation.modules.login

import co.nimblehq.blisskmmic.data.network.helpers.jsonApiException
import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class LoginViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    @Suppress("EmptySecondaryConstructor")
    constructor() : this(false, false, null) {}

    @Suppress("EmptySecondaryConstructor")
    constructor(error: String?) : this(false, false, error) {}
}

class LoginViewModel(private val logInUseCase: LogInUseCase) {

    private val mutableViewState: MutableStateFlow<LoginViewState> =
        MutableStateFlow(LoginViewState())
    private val viewScope = MainScope()

    val viewState: StateFlow<LoginViewState> = mutableViewState

    fun login(email: String, password: String) {
        mutableViewState.update {
            LoginViewState(isLoading = true)
        }
        viewScope.launch {
            logInUseCase(email, password)
                .catch { error ->
                    mutableViewState.update { LoginViewState(isLoading = false, error = error.jsonApiException()) }
                }
                .collect {
                    mutableViewState.update { LoginViewState(isSuccess = true, isLoading = false) }
                }
        }
    }
}
