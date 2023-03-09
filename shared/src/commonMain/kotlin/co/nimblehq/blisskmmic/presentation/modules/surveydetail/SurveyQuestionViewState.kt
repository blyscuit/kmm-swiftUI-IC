package co.nimblehq.blisskmmic.presentation.modules.surveydetail

data class SurveyQuestionViewState(
    val isShowingSubmit: Boolean = false,
    val isLoading: Boolean = false,
    val currentQuestionIndex: Int = 0,
    val isShowingSuccess: Boolean = false
) {
    constructor() : this(currentQuestionIndex = 0)
}
