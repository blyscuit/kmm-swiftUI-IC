package co.nimblehq.blisskmmic.presentation.model

data class SurveyUiModel (
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String
) {
    val largeImageUrl
        get() = imageUrl.plus("l")
}
