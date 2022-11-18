package co.nimblehq.blisskmmic.data.network.core

import co.nimblehq.blisskmmic.helpers.mock.NetworkMockModel
import co.nimblehq.blisskmmic.helpers.mock.NETWORK_MOCK_MODEL_RESULT
import co.nimblehq.blisskmmic.helpers.mock.ktor.jsonMockEngine
import co.nimblehq.jsonapi.model.JsonApiException
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.fail

@ExperimentalCoroutinesApi
class NetworkClientTest {

    val path = "/user"
    val request = HttpRequestBuilder(path = path)

    @Test
    fun `when calling fetch, it returns correct object`() = runTest {
        val engine = jsonMockEngine(NETWORK_MOCK_MODEL_RESULT, path)
        val networkClient = NetworkClient(engine = engine)
        networkClient
            .fetch<NetworkMockModel>(request)
            .collect {
                it.title shouldBe "Hello"
            }
    }

    @Test
    fun `when calling fetch with invalid path, it returns correct object`() = runTest {
        val engine = jsonMockEngine(NETWORK_MOCK_MODEL_RESULT, "")
        val networkClient = NetworkClient(engine = engine)
        networkClient
            .fetch<NetworkMockModel>(request)
            .catch { error ->
                when(error) {
                    is JsonApiException -> error.errors.map { it.code } shouldContain "not_found"
                    else -> fail("Should not return incorrect error type")
                }
            }
            .collect {
                fail("Should not return object")
            }
    }
}
