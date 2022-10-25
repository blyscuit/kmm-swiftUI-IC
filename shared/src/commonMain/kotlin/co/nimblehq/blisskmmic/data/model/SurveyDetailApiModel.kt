package co.nimblehq.blisskmmic.data.model

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
        val text: String
    )
}
