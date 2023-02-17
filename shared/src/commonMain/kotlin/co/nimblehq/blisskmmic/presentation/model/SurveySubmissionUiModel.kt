package co.nimblehq.blisskmmic.presentation.model

import co.nimblehq.blisskmmic.domain.model.SurveySubmission

data class SurveySubmissionUiModel(
    val id: String,
    val answers: List<Answer>
) {

    data class Answer(
        val id: String,
        val answer: String? = null
    )
}

fun SurveySubmissionUiModel.Answer.toSurveySubmission() = SurveySubmission.Answer(
    id,
    answer
)

fun List<SurveySubmissionUiModel>.toSurveySubmission(surveyId: String) = SurveySubmission(
    surveyId,
    map { model ->
        SurveySubmission.Question(
            model.id,
            model.answers.map { it.toSurveySubmission() })
    }
)
