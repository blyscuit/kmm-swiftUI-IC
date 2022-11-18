package co.nimblehq.blisskmmic.helpers.mock

import kotlinx.serialization.Serializable

const val NETWORK_META_MOCK_MODEL_RESULT = """
    {
        "data": {
            "id": 20,
            "type": "type",
            "attributes": {
                "title": "Hello"
            }
        },
        "meta": {
            "page": 1
        }
    }
"""

@Serializable
data class NetworkMetaMockModel(
    val page: Int
)
