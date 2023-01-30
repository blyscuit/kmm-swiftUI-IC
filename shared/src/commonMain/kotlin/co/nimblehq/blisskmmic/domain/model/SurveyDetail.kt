package co.nimblehq.blisskmmic.domain.model

data class SurveyDetail(
    val title: String,
    val description: String,
    val isActive: Boolean,
    val coverImageUrl: String,
    val questions: List<SurveyIncluded>
) {
    data class SurveyIncluded(
        val text: String
    )
}
