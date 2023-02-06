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
        val displayType: DisplayType,
        val displayOrder: Int,
        val answers: List<SurveyAnswer>
    )

    data class SurveyAnswer(
        val id: String,
        val text: String?,
        val displayOrder: Int,
        val inputMaskPlaceholder: String?
    )

    enum class DisplayType {
        DEFAULT,
        INTRO,
        STAR,
        SMILEY,
        HEART,
        NPS,
        TEXTAREA,
        TEXTFIELD,
        OUTRO,
        CHOICE;

        companion object {
            fun from(type: String?): DisplayType =
                values().find { it.name == type } ?: DEFAULT
        }
    }

    val largeImageUrl
        get() = coverImageUrl.plus("l")
}

fun SurveyDetail.toSurveyDetailUiModel() =
    SurveyDetailUiModel(
        title,
        description,
        isActive,
        coverImageUrl,
        questions.map { it.toSurveyDetailUiModel() }
    )

private fun SurveyDetail.SurveyIncluded.toSurveyDetailUiModel() =
    SurveyDetailUiModel.SurveyIncluded(
        id,
        text,
        SurveyDetailUiModel.DisplayType.from(displayType.uppercase()),
        displayOrder,
        answers.map { it.toSurveyDetailUiModel() }
    )

private fun SurveyDetail.SurveyAnswer.toSurveyDetailUiModel() =
    SurveyDetailUiModel.SurveyAnswer(
        id,
        text,
        displayOrder,
        inputMaskPlaceholder
    )
