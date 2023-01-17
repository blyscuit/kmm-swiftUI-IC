package co.nimblehq.blisskmmic.presentation.modules.surveyselection

import co.nimblehq.blisskmmic.MR
import co.nimblehq.blisskmmic.domain.model.AppVersion
import co.nimblehq.blisskmmic.domain.model.Survey
import co.nimblehq.blisskmmic.domain.model.User
import co.nimblehq.blisskmmic.domain.platform.datetime.DateFormat
import co.nimblehq.blisskmmic.domain.platform.datetime.DateTimeFormatter
import co.nimblehq.blisskmmic.domain.usecase.GetAppVersionUseCase
import co.nimblehq.blisskmmic.domain.usecase.GetCurrentDateUseCase
import co.nimblehq.blisskmmic.domain.usecase.GetProfileUseCase
import co.nimblehq.blisskmmic.domain.usecase.SurveyListUseCase
import co.nimblehq.blisskmmic.presentation.model.AccountUiModel
import co.nimblehq.blisskmmic.presentation.model.SurveyHeaderUiModel
import co.nimblehq.blisskmmic.presentation.model.SurveyUiModel
import co.nimblehq.blisskmmic.presentation.modules.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val FETCH_MORE_TRIGGER = 2

data class SurveySelectionViewState(
    val isLoading: Boolean = true,
    val surveyHeaderUiModel: SurveyHeaderUiModel? = null,
    val accountUiModel: AccountUiModel? = null,
    val surveys: List<SurveyUiModel> = listOf()
) {
    constructor() : this(true)
}

class SurveySelectionViewModel(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getAppVersionUseCase: GetAppVersionUseCase,
    private val surveyListUseCase: SurveyListUseCase,
    private val dateTimeFormatter: DateTimeFormatter
) : BaseViewModel() {

    private val mutableViewState: MutableStateFlow<SurveySelectionViewState> =
        MutableStateFlow(SurveySelectionViewState())

    private var currentPage = 1
    private var fetchingSurvey = true

    val viewState: StateFlow<SurveySelectionViewState> = mutableViewState

    fun fetch() {
        setStateLoading()
        viewModelScope.launch {
            getProfile()
                .combine(getDate()) { user, dateText -> Pair(user, dateText) }
                .combine(getAppVersion()) { userDate, version ->
                    Triple(userDate.first, userDate.second, version)
                }
                .combine(fetchSurvey(currentPage)) { userData, survey ->
                    Pair(userData, survey)
                }
                .collect {
                    updateHeaderState(it.first.first, it.first.second, it.first.third)
                    updateSurveyState(it.second)
                }
        }
    }

    fun checkFetchMore(itemIndex: Int) {
        if(itemIndex >= viewState.value.surveys.size - FETCH_MORE_TRIGGER) {
            fetchMoreSurvey()
        }
    }

    private fun fetchSurvey(page: Int): Flow<List<SurveyUiModel>> {
        return flow {
            surveyListUseCase(page)
                .catch { emit(listOf()) }
                .collect {
                    currentPage = page+1
                    emit(handleSurveySuccess(it))
                }
        }
    }

    private fun fetchMoreSurvey() {
        if(fetchingSurvey) { return }
        fetchingSurvey = true
        viewModelScope.launch {
            fetchSurvey(currentPage)
                .collect {
                    updateSurveyState(it)
                }
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

    private fun handleSurveySuccess(surveys: List<Survey>): List<SurveyUiModel> {
        return surveys.map { SurveyUiModel(it) }
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

    private fun updateSurveyState(surveys: List<SurveyUiModel>) {
        mutableViewState.update {
            SurveySelectionViewState(
                false,
                it.surveyHeaderUiModel,
                it.accountUiModel,
                it.surveys + surveys
            )
        }
        fetchingSurvey = false
    }
}
