package co.nimblehq.blisskmmic.data.model

import co.nimblehq.blisskmmic.domain.model.Survey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SurveyApiModel(
    val id: String,
    val title: String,
    val description: String,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("cover_image_url") val coverImageUrl: String,
    @SerialName("survey_type") val surveyType: String
)

fun SurveyApiModel.toSurvey() = Survey(
    id,
    coverImageUrl,
    title,
    description
)
