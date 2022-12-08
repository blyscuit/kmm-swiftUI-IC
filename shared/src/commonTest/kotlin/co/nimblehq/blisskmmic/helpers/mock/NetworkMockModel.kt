package co.nimblehq.blisskmmic.helpers.mock

import kotlinx.serialization.Serializable

const val NETWORK_MOCK_MODEL_RESULT = """
    {
        "data": {
            "id": 20,
            "type": "type",
            "attributes": {
                "title": "Hello"
            }
        }
    }
"""

@Serializable
data class NetworkMockModel(
    val title: String
)
