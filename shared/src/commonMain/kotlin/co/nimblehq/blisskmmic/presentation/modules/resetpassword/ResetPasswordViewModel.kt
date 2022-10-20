package co.nimblehq.blisskmmic.presentation.modules.resetpassword

import co.nimblehq.blisskmmic.data.network.helpers.jsonApiException
import co.nimblehq.blisskmmic.domain.usecase.ResetPasswordUseCase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ResetPasswordViewState(
    val successResponse: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    @Suppress("EmptySecondaryConstructor")
    constructor() : this(null, false, null) {}

    @Suppress("EmptySecondaryConstructor")
    constructor(error: String?) : this(null, false, error) {}
}

class ResetPasswordViewModel(private val resetPasswordUseCase: ResetPasswordUseCase) {

    private val mutableViewState: MutableStateFlow<ResetPasswordViewState> =
        MutableStateFlow(ResetPasswordViewState())
    private val viewScope = MainScope()

    val viewState: StateFlow<ResetPasswordViewState> = mutableViewState

    fun reset(email: String) {
        mutableViewState.update {
            ResetPasswordViewState(isLoading = true)
        }
        viewScope.launch {
            resetPasswordUseCase(email)
                .catch { error ->
                    mutableViewState.update { ResetPasswordViewState(isLoading = false, error = error.jsonApiException()) }
                }
                .collect { response ->
                    mutableViewState.update { ResetPasswordViewState(response, false) }
                }
        }
    }
}
