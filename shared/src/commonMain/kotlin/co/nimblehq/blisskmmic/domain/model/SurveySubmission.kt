package co.nimblehq.blisskmmic.domain.model

data class SurveySubmission(
    val id: String,
    val questions: List<Question>
) {

    data class Question(
        val id: String,
        val answers: List<Answer>
    )

    data class Answer(
        val id: String,
        val answer: String? = null
    )
}
