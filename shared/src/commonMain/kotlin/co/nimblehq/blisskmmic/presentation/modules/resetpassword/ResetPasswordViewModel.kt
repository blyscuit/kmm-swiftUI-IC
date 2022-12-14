package co.nimblehq.blisskmmic.presentation.modules.resetpassword

import co.nimblehq.blisskmmic.MR
import co.nimblehq.blisskmmic.data.network.helpers.toErrorMessage
import co.nimblehq.blisskmmic.domain.usecase.ResetPasswordUseCase
import co.nimblehq.blisskmmic.presentation.model.NotificationUiModel
import co.nimblehq.blisskmmic.presentation.modules.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ResetPasswordViewState(
    val successNotification: NotificationUiModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    constructor() : this(null, false, null)
    constructor(error: String?) : this(null, false, error)
}

class ResetPasswordViewModel(private val resetPasswordUseCase: ResetPasswordUseCase) :
    BaseViewModel() {

    private val mutableViewState: MutableStateFlow<ResetPasswordViewState> =
        MutableStateFlow(ResetPasswordViewState())

    val viewState: StateFlow<ResetPasswordViewState> = mutableViewState

    fun reset(email: String) {
        setStateLoading()
        viewModelScope.launch {
            resetPasswordUseCase(email)
                .catch { handleResetPasswordError(it) }
                .collect { _ -> resetPasswordSuccess() }
        }
    }

    private fun setStateLoading() {
        mutableViewState.update {
            ResetPasswordViewState(isLoading = true)
        }
    }

    private fun handleResetPasswordError(error: Throwable) {
        mutableViewState.update {
            ResetPasswordViewState(error.toErrorMessage())
        }
    }

    private fun resetPasswordSuccess() {
        val notification = NotificationUiModel(
            MR.strings.reset_password_success_notification_title,
            MR.strings.reset_password_success_notification_message
        )
        mutableViewState.update { ResetPasswordViewState(notification, false) }
    }
}
