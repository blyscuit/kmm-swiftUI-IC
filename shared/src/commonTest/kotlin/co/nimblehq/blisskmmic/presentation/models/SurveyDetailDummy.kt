package co.nimblehq.blisskmmic.presentation.models

import co.nimblehq.blisskmmic.domain.model.SurveyDetail

val surveyIncludedDummy = SurveyDetail.SurveyIncluded(
    id = "",
    text = "",
    helpText = "",
    displayType = "",
    displayOrder = 1,
    answers = emptyList()
)

fun surveyDetailDummy(items: Int) = SurveyDetail(
    title = "",
    description = "",
    isActive = true,
    coverImageUrl = "",
    questions = (0..items).map { surveyIncludedDummy }
)
