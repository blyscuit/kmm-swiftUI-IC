package co.nimblehq.blisskmmic.presentation.modules.surveyselection

data class SurveySelectionViewState(
    val isLoading: Boolean = false,
    val surveyHeaderUiModel = SurveyHeaderUiModel? = null
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
            getCurrentDateUseCase()
                .zip(getProfileUseCase())
                .catch { handleUserError(it) }
                .collect { date, user -> handleUserSuccess(date, user) }
        }
    }

    private fun setStateLoading() {
        mutableViewState.update {
            SurveySelectionViewState(isLoading = true)
        }
    }

    private fun handleUserError(error: Throwable) {
        val surveyHeader = SurveyHeaderUiModel("", "")
        mutableViewState.update {
            SurveySelectionViewState(false, SurveyHeaderUiModel)
        }
    }

    private fun handleUserSuccess(dateComponent: DateComponent, user: User) {
        dateText = dateTimeFormatter.getFormattedString(dateComponent.timeInterval, DateFormat.DayOfWeekMonthDay)
        val surveyHeader = SurveyHeaderUiModel(
            user.avatarUrl,
            dateText
        )
        mutableViewState.update { SurveySelectionViewState(false, surveyHeader) }
    }
}
