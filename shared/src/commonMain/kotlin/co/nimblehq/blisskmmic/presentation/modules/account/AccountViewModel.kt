package co.nimblehq.blisskmmic.presentation.modules.account

import co.nimblehq.blisskmmic.domain.usecase.LogOutUseCase
import co.nimblehq.blisskmmic.presentation.model.AccountUiModel
import co.nimblehq.blisskmmic.presentation.modules.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class AccountViewState(
    val isLogout: Boolean = false,
    val accountUiModel: AccountUiModel? = null,
    val isLoading: Boolean = false
) {
    constructor() : this(false)
}

class AccountViewModel: BaseViewModel, KoinComponent {

    private val logOutUseCase: LogOutUseCase by inject()

    private val accountUiModel: AccountUiModel?

    private val mutableViewState: MutableStateFlow<AccountViewState> =
        MutableStateFlow(AccountViewState())

    val viewState: StateFlow<AccountViewState> = mutableViewState

    constructor(accountUiModel: AccountUiModel?) {
        this.accountUiModel = accountUiModel
        mutableViewState.update {
            AccountViewState(accountUiModel = accountUiModel)
        }
    }

    fun logOut() {
        setStateLoading(true)
        viewModelScope.launch {
            logOutUseCase()
                .catch { _ -> setStateLoading(false) }
                .collect { setStateLogout() }
        }
    }

    private fun setStateLoading(loading: Boolean) {
        mutableViewState.update {
            AccountViewState(false, accountUiModel, loading)
        }
    }

    private fun setStateLogout() {
        mutableViewState.update {
            AccountViewState(true, accountUiModel, false)
        }
    }
}
