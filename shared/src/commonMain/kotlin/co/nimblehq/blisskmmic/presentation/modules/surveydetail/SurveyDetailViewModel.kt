package co.nimblehq.blisskmmic.presentation.modules.surveydetail

import co.nimblehq.blisskmmic.data.network.helpers.toErrorMessage
import co.nimblehq.blisskmmic.domain.model.SurveyDetail
import co.nimblehq.blisskmmic.domain.usecase.GetSurveyDetailUseCase
import co.nimblehq.blisskmmic.domain.usecase.SubmitSurveyUseCase
import co.nimblehq.blisskmmic.presentation.model.SurveyDetailUiModel
import co.nimblehq.blisskmmic.presentation.model.SurveySubmissionUiModel
import co.nimblehq.blisskmmic.presentation.model.toSurveyDetailUiModel
import co.nimblehq.blisskmmic.presentation.model.toSurveySubmission
import co.nimblehq.blisskmmic.presentation.modules.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SurveyDetailViewState(
    val surveyDetail: SurveyDetailUiModel? = null,
    val isLoading: Boolean = false,
    val isShowingQuestion: Boolean = false,
    val error: String? = null
) {
    constructor() : this(isLoading = false)
    constructor(error: String?) : this(null, false, false, error)
}

@Suppress("TooManyFunctions")
class SurveyDetailViewModel(
    private val getSurveyDetailUseCase: GetSurveyDetailUseCase,
    private val submitSurveyUseCase: SubmitSurveyUseCase,
    private var surveyId: String? = null,
    private var answers: MutableList<SurveySubmissionUiModel> = mutableListOf()
): BaseViewModel() {

    private val mutableViewState: MutableStateFlow<SurveyDetailViewState> =
        MutableStateFlow(SurveyDetailViewState())
    private val questionMutableViewState: MutableStateFlow<SurveyQuestionViewState> =
        MutableStateFlow(SurveyQuestionViewState())

    val viewState: StateFlow<SurveyDetailViewState> = mutableViewState
    val questionViewState: StateFlow<SurveyQuestionViewState> = questionMutableViewState

    fun setSurveyId(id: String) {
        surveyId = id
    }

    fun getDetail() {
        surveyId?.let {
            setStateLoading()
            viewModelScope.launch {
                getSurveyDetailUseCase(it)
                    .catch { error -> handleError(error) }
                    .collect { handleSurveyDetail(it) }
            }
        }
    }

    fun showQuestion() {
        answers = mutableListOf()
        val currentState = viewState.value
        mutableViewState.update {
            SurveyDetailViewState(
                currentState.surveyDetail,
                currentState.isLoading,
                true,
                currentState.error
            )
        }
        if(!currentState.isLoading && currentState.surveyDetail == null) {
            getDetail()
        }
    }

    fun addAnswer(values: List<SurveySubmissionUiModel.Answer>) {
        viewState.value.surveyDetail?.questions?.get(questionViewState.value.currentQuestionIndex)?.let {
            val submission = SurveySubmissionUiModel(it.id, values)
            answers.add(submission)
        }
        setNextQuestionState()
    }

    fun submitAnswer() {
        with(questionViewState.value) {
            if (!isLoading) {
                questionMutableViewState.update {
                    SurveyQuestionViewState(
                        isShowingSubmit = isShowingSubmit,
                        isLoading = true,
                        currentQuestionIndex = currentQuestionIndex
                    )
                }
                performSubmitAnswer()
            }
        }
    }

    private fun performSubmitAnswer() {
        surveyId?.let {
            val submission = answers.toSurveySubmission(it)
            viewModelScope.launch {
                submitSurveyUseCase(submission)
                    .catch { handleSubmitError() }
                    .collect { handleSubmitSuccess() }
            }
        }
    }

    private fun setStateLoading() {
        val currentState = viewState.value
        mutableViewState.update {
            SurveyDetailViewState(
                isLoading = true,
                isShowingQuestion = currentState.isShowingQuestion
            )
        }
    }

    private fun handleError(error: Throwable) {
        mutableViewState.update { SurveyDetailViewState(isLoading = false, error = error.toErrorMessage()) }
    }

    private fun handleSurveyDetail(detail: SurveyDetail) {
        val currentState = viewState.value
        mutableViewState.update {
            SurveyDetailViewState(
                surveyDetail = detail.toSurveyDetailUiModel(),
                isLoading = false,
                isShowingQuestion = currentState.isShowingQuestion
            )
        }
    }

    private fun setNextQuestionState() {
        val questionSize = viewState.value.surveyDetail?.questions?.size ?: 0
        val currentState = questionViewState.value
        var nextQuestionIndex = currentState.currentQuestionIndex + 1
        val isFinalQuestion = nextQuestionIndex >= (questionSize - 1)
        questionMutableViewState.update {
            SurveyQuestionViewState(
                isShowingSubmit = isFinalQuestion,
                isLoading = currentState.isLoading,
                currentQuestionIndex = nextQuestionIndex
            )
        }
    }

    private fun handleSubmitError() {
        questionMutableViewState.update {
            SurveyQuestionViewState(
                currentQuestionIndex = questionViewState.value.currentQuestionIndex
            )
        }
    }

    private fun handleSubmitSuccess() {
        questionMutableViewState.update {
            SurveyQuestionViewState(
                isShowingSubmit = true,
                isLoading = false,
                currentQuestionIndex = questionViewState.value.currentQuestionIndex,
                isShowingSuccess = true
            )
        }
    }
}
