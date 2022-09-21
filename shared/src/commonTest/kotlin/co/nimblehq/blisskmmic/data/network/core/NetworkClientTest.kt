package co.nimblehq.blisskmmic.data.network.core

import co.nimblehq.blisskmmic.Greeting
import co.nimblehq.blisskmmic.helpers.mock.NetworkMockModel
import co.nimblehq.blisskmmic.helpers.mock.NETWORK_MOCK_MODEL_RESULT
import co.nimblehq.blisskmmic.helpers.mock.ktor.jsonMockEngine
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NetworkClientTest {

    val engine = jsonMockEngine(NETWORK_MOCK_MODEL_RESULT)

    @Test
    fun testMockModel() = runTest {
        val networkClient = NetworkClient(engine = engine)
        networkClient
            .fetch<NetworkMockModel>(HttpRequestBuilder())
            .collect {
                assertEquals(it.title, "Hello")
            }
    }
}
