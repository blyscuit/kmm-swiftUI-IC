package co.nimblehq.blisskmmic.data.network.target

import co.nimblehq.blisskmmic.data.network.helpers.TargetType
import co.nimblehq.blisskmmic.domain.model.SurveySubmission
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class SubmitSurveyTargetType(submission: SurveySubmission):
    TargetType<SubmitSurveyTargetType.SubmitSurveyInput> {

    @Serializable
    data class SubmitSurveyInput(
        @SerialName("survey_id")
        val id: String,
        val questions: List<Question>
    ) {

        @Serializable
        data class Question(
            val id: String,
            val answers: List<Answer>
        )

        @Serializable
        data class Answer(
            val id: String,
            val answer: String? = null
        )
    }

    override val path = "responses"
    override val method = HttpMethod.Post
    override val body = submission.toSubmitSurveyInput()
}

fun SurveySubmission.toSubmitSurveyInput() = SubmitSurveyTargetType.SubmitSurveyInput(
    id,
    questions.map { it.toSubmitSurveyInput() }
)

private fun SurveySubmission.Question.toSubmitSurveyInput() =
    SubmitSurveyTargetType.SubmitSurveyInput.Question(
        id,
        answers.map { it.toSubmitSurveyInput() }
    )

private fun SurveySubmission.Answer.toSubmitSurveyInput() =
    SubmitSurveyTargetType.SubmitSurveyInput.Answer(id, answer)
