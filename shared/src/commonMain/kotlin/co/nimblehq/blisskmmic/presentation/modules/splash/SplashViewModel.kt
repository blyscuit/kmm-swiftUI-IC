package co.nimblehq.blisskmmic.presentation.modules.splash

import co.nimblehq.blisskmmic.domain.usecase.CheckLoginUseCase
import co.nimblehq.blisskmmic.presentation.modules.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class SplashViewState(
    val isLogin: Boolean = false,
    val isLoading: Boolean = false
) {
    constructor() : this(false)
}

class SplashViewModel(private val checkLoginUseCase: CheckLoginUseCase) :
    BaseViewModel() {

    private val mutableViewState: MutableStateFlow<SplashViewState> =
        MutableStateFlow(SplashViewState())

    val viewState: StateFlow<SplashViewState> = mutableViewState

    fun checkLogin() {
        setStateLoading()
        viewModelScope.launch {
            checkLoginUseCase()
                .catch { _ -> setState(false) }
                .collect{ setState(it) }
        }
    }

    private fun setStateLoading() {
        mutableViewState.update {
            SplashViewState(isLoading = true)
        }
    }

    private fun setState(login: Boolean) {
        mutableViewState.update {
            SplashViewState(login, false)
        }
    }
}
