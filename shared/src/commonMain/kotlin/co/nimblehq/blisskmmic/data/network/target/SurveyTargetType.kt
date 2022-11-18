package co.nimblehq.blisskmmic.data.network.target

import co.nimblehq.blisskmmic.data.network.helpers.TargetType
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class SurveyTargetType: TargetType

class SurveySelectionTargetType(page: Int = 1, size: Int = 3): SurveyTargetType() {

    @Serializable
    data class SurveySelectionInput(
        @SerialName("page[number]")
        val pageNumber: Int,
        @SerialName("page[size]")
        val pageSize: Int
    )

    override var path = "surveys"
    override var method = HttpMethod.Post
    override var body = SurveySelectionInput(page, size)
    override var headers: Map<String, String>? = null
}
