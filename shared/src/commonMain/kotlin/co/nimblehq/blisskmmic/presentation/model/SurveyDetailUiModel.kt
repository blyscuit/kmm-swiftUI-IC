package co.nimblehq.blisskmmic.presentation.model

import co.nimblehq.blisskmmic.domain.model.SurveyDetail

data class SurveyDetailUiModel (
    val title: String,
    val description: String,
    val isActive: Boolean,
    val coverImageUrl: String,
    val questions: List<String>
) {
    val largeImageUrl
        get() = coverImageUrl.plus("l")

    constructor(detail: SurveyDetail):
        this(
            detail.title,
            detail.description,
            detail.isActive,
            detail.coverImageUrl,
            detail.questions.map { it.text }
        )
}
