package co.nimblehq.blisskmmic.presentation.modules.surveyselection

import co.nimblehq.blisskmmic.MR
import co.nimblehq.blisskmmic.domain.model.DateComponent
import co.nimblehq.blisskmmic.domain.model.User
import co.nimblehq.blisskmmic.domain.platform.datetime.DateFormat
import co.nimblehq.blisskmmic.domain.platform.datetime.DateTimeFormatter
import co.nimblehq.blisskmmic.domain.usecase.GetCurrentDateUseCase
import co.nimblehq.blisskmmic.domain.usecase.GetProfileUseCase
import co.nimblehq.blisskmmic.presentation.model.SurveyHeaderUiModel
import co.nimblehq.blisskmmic.presentation.modules.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class SurveySelectionViewState(
    val isLoading: Boolean = true,
    val surveyHeaderUiModel: SurveyHeaderUiModel? = null
) {
    constructor() : this(true)
}

class SurveySelectionViewModel(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getProfileUseCase: GetProfileUseCase,
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
                .collect { updateHeaderState(it.first, it.second) }
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

    private fun setStateLoading() {
        mutableViewState.update {
            SurveySelectionViewState(isLoading = true)
        }
    }

    private fun handleDateSuccess(dateComponent: DateComponent): String {
        return dateTimeFormatter.getFormattedString(dateComponent.timeInterval, DateFormat.DayOfWeekMonthDay)
    }

    private fun updateHeaderState(user: User?, dateText: String) {
        val surveyHeader = SurveyHeaderUiModel(
            user?.avatarUrl,
            dateText,
            MR.strings.common_today
        )
        mutableViewState.update { SurveySelectionViewState(false, surveyHeader) }
    }
}
