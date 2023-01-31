package co.nimblehq.blisskmmic.presentation.modules.surveydetail

import co.nimblehq.blisskmmic.data.network.helpers.toErrorMessage
import co.nimblehq.blisskmmic.domain.model.SurveyDetail
import co.nimblehq.blisskmmic.domain.usecase.GetSurveyDetailUseCase
import co.nimblehq.blisskmmic.presentation.model.SurveyDetailUiModel
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
    val error: String? = null,
) {
    constructor() : this(isLoading = false)
    constructor(error: String?) : this(null, false, false, error)
}

class SurveyDetailViewModel(
    private val getSurveyDetailUseCase: GetSurveyDetailUseCase,
    private var surveyId: String? = null
): BaseViewModel() {

    private val mutableViewState: MutableStateFlow<SurveyDetailViewState> =
        MutableStateFlow(SurveyDetailViewState())

    val viewState: StateFlow<SurveyDetailViewState> = mutableViewState

    fun setSurveyId(id: String) {
        surveyId = id
    }

    fun getDetail() {
        surveyId ?: return
        val id = surveyId ?: ""
        setStateLoading()
        viewModelScope.launch {
            getSurveyDetailUseCase(id)
                .catch { error -> handleError(error) }
                .collect { handleSurveyDetail(it) }
        }
    }

    fun showQuestion() {
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
                surveyDetail = SurveyDetailUiModel(detail),
                isLoading = false,
                isShowingQuestion = currentState.isShowingQuestion
            )
        }
    }
}
