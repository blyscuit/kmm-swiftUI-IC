package co.nimblehq.blisskmmic.data.network.core

import co.nimblehq.blisskmmic.helpers.mock.NetworkMockModel
import co.nimblehq.blisskmmic.helpers.mock.NETWORK_MOCK_MODEL_RESULT
import co.nimblehq.blisskmmic.helpers.mock.ktor.jsonMockEngine
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class NetworkClientTest {

    val engine = jsonMockEngine(NETWORK_MOCK_MODEL_RESULT)

    @Test
    fun testMockModel() = runTest {
        val networkClient = NetworkClient(engine = engine)
        networkClient
            .fetch<NetworkMockModel>(HttpRequestBuilder())
            .collect {
                it.title shouldBe "Hello"
            }
    }
}
