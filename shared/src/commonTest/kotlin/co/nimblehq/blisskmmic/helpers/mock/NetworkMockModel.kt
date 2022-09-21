package co.nimblehq.blisskmmic.helpers.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val NETWORK_MOCK_MODEL_RESULT = """
    {
    "title": "Hello"
    }
"""

@Serializable
data class NetworkMockModel(
    @SerialName("title")
    val title: String
)
