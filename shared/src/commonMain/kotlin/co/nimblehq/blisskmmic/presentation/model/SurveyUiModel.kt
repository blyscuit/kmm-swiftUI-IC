package co.nimblehq.blisskmmic.presentation.model

import co.nimblehq.blisskmmic.domain.model.Survey

data class SurveyUiModel (
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String
) {
    val largeImageUrl
        get() = imageUrl.plus("l")

    constructor(survey: Survey):
        this(
            survey.id,
            survey.imageUrl,
            survey.title,
            survey.description
        )
}
