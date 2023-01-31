package co.nimblehq.blisskmmic.presentation.model

import co.nimblehq.blisskmmic.domain.model.SurveyDetail

data class SurveyDetailUiModel (
    val title: String,
    val description: String,
    val isActive: Boolean,
    val coverImageUrl: String,
    val questions: List<SurveyIncluded>
) {
    data class SurveyIncluded(
        val id: String,
        val text: String,
        val displayType: String,
        val displayOrder: Int,
        val answers: List<SurveyAnswer>
    ) {

        constructor(survey: SurveyDetail.SurveyIncluded):
            this(
                survey.id,
                survey.text,
                survey.displayType,
                survey.displayOrder,
                survey.answers.map { SurveyAnswer(it) }
            )
    }

    data class SurveyAnswer(
        val id: String,
        val text: String,
        val displayOrder: Int,
        val inputMaskPlaceholder: String
    ) {

        constructor(answer: SurveyDetail.SurveyAnswer):
            this(
                answer.id,
                answer.text,
                answer.displayOrder,
                answer.inputMaskPlaceholder
            )
    }

    companion object {

        const val Default = "default"
        const val Intro = "intro"
        const val Star = "star"
        const val Smiley = "smiley"
        const val Heart = "heart"
        const val NPS = "nps"
        const val TextArea = "textarea"
        const val TextField = "textfield"
        const val Outro = "outro"
        const val Choice = "choice"
    }

    val largeImageUrl
        get() = coverImageUrl.plus("l")

    constructor(detail: SurveyDetail):
        this(
            detail.title,
            detail.description,
            detail.isActive,
            detail.coverImageUrl,
            detail.questions.map { SurveyIncluded(it) }
        )
}
