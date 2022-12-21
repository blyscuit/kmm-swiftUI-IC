package co.nimblehq.blisskmmic.presentation.modules.surveyselection

import co.nimblehq.blisskmmic.MR
import co.nimblehq.blisskmmic.domain.model.AppVersion
import co.nimblehq.blisskmmic.domain.model.User
import co.nimblehq.blisskmmic.domain.platform.datetime.DateFormat
import co.nimblehq.blisskmmic.domain.platform.datetime.DateTimeFormatter
import co.nimblehq.blisskmmic.domain.usecase.GetAppVersionUseCase
import co.nimblehq.blisskmmic.domain.usecase.GetCurrentDateUseCase
import co.nimblehq.blisskmmic.domain.usecase.GetProfileUseCase
import co.nimblehq.blisskmmic.presentation.model.AccountUiModel
import co.nimblehq.blisskmmic.presentation.model.SurveyHeaderUiModel
import co.nimblehq.blisskmmic.presentation.modules.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class SurveySelectionViewState(
    val isLoading: Boolean = true,
    val surveyHeaderUiModel: SurveyHeaderUiModel? = null,
    val accountUiModel: AccountUiModel? = null
) {
    constructor() : this(true)
}

class SurveySelectionViewModel(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getAppVersionUseCase: GetAppVersionUseCase,
    private val dateTimeFormatter: DateTimeFormatter
) : BaseViewModel() {

    private val mutableViewState: MutableStateFlow<SurveySelectionViewState> =
        MutableStateFlow(SurveySelectionViewState())

    val viewState: StateFlow<SurveySelectionViewState> = mutableViewState

    fun fetch() {
        setStateLoading()
        viewModelScope.launch {
            getProfile()
                .combine(getDate()) { user, dateText -> Pair(user, dateText) }
                .combine(getAppVersion()) { userDate, version ->
                    Triple(userDate.first, userDate.second, version)
                }
                .collect { updateHeaderState(it.first, it.second, it.third) }
        }
    }

    private fun getProfile(): Flow<User?> {
        return flow {
            getProfileUseCase()
                .catch { emit(null) }
                .collect { emit(it) }
        }
    }

    private fun getDate(): Flow<String> {
        return flow {
            getCurrentDateUseCase()
                .catch { emit("") }
                .collect { emit(handleDateSuccess(it)) }
        }
    }

    private fun getAppVersion(): Flow<String> {
        return flow {
            getAppVersionUseCase()
                .catch { emit("()") }
                .collect { emit(handleVersionSuccess(it)) }
        }
    }

    private fun setStateLoading() {
        mutableViewState.update {
            SurveySelectionViewState(isLoading = true)
        }
    }

    private fun handleDateSuccess(timeInterval: Long): String {
        return dateTimeFormatter.getFormattedString(timeInterval, DateFormat.DayOfWeekMonthDay)
    }

    private fun handleVersionSuccess(appVersion: AppVersion): String {
        return "v${appVersion.appVersion} (${appVersion.buildNumber})"
    }

    private fun updateHeaderState(user: User?, dateText: String, version: String) {
        val surveyHeader = SurveyHeaderUiModel(
            user?.avatarUrl,
            dateText,
            MR.strings.common_today
        )
        val account = AccountUiModel(
            user?.avatarUrl,
            user?.name ?: "",
            version
        )
        mutableViewState.update {
            SurveySelectionViewState(false, surveyHeader, account)
        }
    }
}
