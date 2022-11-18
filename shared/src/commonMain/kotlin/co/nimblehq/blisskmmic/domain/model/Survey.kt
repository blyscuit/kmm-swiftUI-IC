package co.nimblehq.blisskmmic.domain.model

import kotlinx.serialization.SerialName

data class Survey(
    val title: String,
    val description: String,
    val isActive: Boolean,
    val coverImageUrl: String,
    val surveyType: String
)
