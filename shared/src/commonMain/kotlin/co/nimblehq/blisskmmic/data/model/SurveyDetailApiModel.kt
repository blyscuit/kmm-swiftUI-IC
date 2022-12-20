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
        val text: String
    )

    fun toSurveyDetail(): SurveyDetail = SurveyDetail(
        title,
        description,
        isActive,
        coverImageUrl,
        questions.map { SurveyDetail.SurveyIncluded(it.text) }
    )
}
