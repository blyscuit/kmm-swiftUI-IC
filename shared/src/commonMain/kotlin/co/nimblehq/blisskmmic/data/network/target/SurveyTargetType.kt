package co.nimblehq.blisskmmic.data.network.target

import co.nimblehq.blisskmmic.data.network.helpers.TargetType
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class SurveyTargetType<T>: TargetType<T>

class SurveySelectionTargetType(page: Int = 1, size: Int = 3):
    SurveyTargetType<SurveySelectionTargetType.SurveySelectionInput>() {

    @Serializable
    data class SurveySelectionInput(
        @SerialName("page[number]")
        val pageNumber: Int,
        @SerialName("page[size]")
        val pageSize: Int
    )

    override var path = "surveys"
    override var method = HttpMethod.Get
    override var body = SurveySelectionInput(page, size)
}
