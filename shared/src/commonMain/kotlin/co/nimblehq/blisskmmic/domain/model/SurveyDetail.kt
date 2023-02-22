package co.nimblehq.blisskmmic.domain.model

data class SurveyDetail(
    val title: String,
    val description: String,
    val isActive: Boolean,
    val coverImageUrl: String,
    val questions: List<SurveyIncluded>
) {
    data class SurveyIncluded(
        val id: String,
        val text: String,
        val helpText: String?,
        val displayType: String,
        val displayOrder: Int,
        val answers: List<SurveyAnswer>
    )

    data class SurveyAnswer(
        val id: String,
        val text: String?,
        val displayOrder: Int,
        val inputMaskPlaceholder: String?
    )
}
