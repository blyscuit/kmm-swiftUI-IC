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
    val error: String? = null,
) {
    constructor() : this(isLoading = false)
    constructor(error: String?) : this(null, false, error)
}

class SurveyDetailViewModel(
    private val getSurveyDetailUseCase: GetSurveyDetailUseCase,
    private val surveyId: String
): BaseViewModel() {

    private val mutableViewState: MutableStateFlow<SurveyDetailViewState> =
        MutableStateFlow(SurveyDetailViewState())

    val viewState: StateFlow<SurveyDetailViewState> = mutableViewState

    fun getDetail() {
        setStateLoading()
        viewModelScope.launch {
            getSurveyDetailUseCase(surveyId)
                .catch { error -> handleLoginError(error) }
                .collect { handleSurveyDetail(it) }
        }
    }

    private fun setStateLoading() {
        mutableViewState.update {
            SurveyDetailViewState(isLoading = true)
        }
    }

    private fun handleLoginError(error: Throwable) {
        mutableViewState.update { SurveyDetailViewState(isLoading = false, error = error.toErrorMessage()) }
    }

    private fun handleSurveyDetail(detail: SurveyDetail) {
        mutableViewState.update {
            SurveyDetailViewState(surveyDetail = SurveyDetailUiModel(detail), isLoading = false)
        }
    }
}
