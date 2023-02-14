package co.nimblehq.blisskmmic.data.model

import co.nimblehq.blisskmmic.domain.model.SurveyDetail
import co.nimblehq.blisskmmic.domain.model.Token
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SurveyDetailApiModel(
    val title: String,
    val description: String,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("cover_image_url") val coverImageUrl: String,
    @SerialName("survey_type") val surveyType: String,
    val questions: List<SurveyIncluded>
) {

    @Serializable
    data class SurveyIncluded(
        val id: String,
        val text: String,
        @SerialName("help_text") val helpText: String?,
        @SerialName("display_type") val displayType: String,
        @SerialName("display_order") val displayOrder: Int,
        val answers: List<SurveyAnswer>
    ) {
        fun toSurveyDetail(): SurveyDetail.SurveyIncluded = SurveyDetail.SurveyIncluded(
            id,
            text,
            helpText,
            displayType,
            displayOrder,
            answers.map { it.toSurveyDetail() }
        )
    }

    @Serializable
    data class SurveyAnswer(
        val id: String,
        val text: String?,
        @SerialName("display_order") val displayOrder: Int,
        @SerialName("input_mask_placeholder") val inputMaskPlaceholder: String?
    ) {
        fun toSurveyDetail(): SurveyDetail.SurveyAnswer = SurveyDetail.SurveyAnswer(
            id,
            text,
            displayOrder,
            inputMaskPlaceholder
        )
    }

    fun toSurveyDetail(): SurveyDetail = SurveyDetail(
        title,
        description,
        isActive,
        coverImageUrl,
        questions.map { it.toSurveyDetail() }
    )
}
