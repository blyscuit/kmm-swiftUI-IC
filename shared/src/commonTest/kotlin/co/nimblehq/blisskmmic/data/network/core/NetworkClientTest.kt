package co.nimblehq.blisskmmic.data.network.core

import co.nimblehq.blisskmmic.Greeting
import co.nimblehq.blisskmmic.helpers.mock.NetworkMockModel
import co.nimblehq.blisskmmic.helpers.mock.NetworkMockModel_RESULT
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NetworkClientTest {

    val engine = MockEngine { request ->
        respond(
            content = NetworkMockModel_RESULT,
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

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