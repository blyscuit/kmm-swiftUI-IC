package co.nimblehq.blisskmmic.data.network.core

import app.cash.turbine.test
import co.nimblehq.blisskmmic.BuildKonfig
import co.nimblehq.blisskmmic.data.network.helpers.API_VERSION
import co.nimblehq.blisskmmic.helpers.mock.NETWORK_META_MOCK_MODEL_RESULT
import co.nimblehq.blisskmmic.helpers.mock.NetworkMockModel
import co.nimblehq.blisskmmic.helpers.mock.NETWORK_MOCK_MODEL_RESULT
import co.nimblehq.blisskmmic.helpers.mock.NetworkMetaMockModel
import co.nimblehq.blisskmmic.helpers.mock.ktor.jsonMockEngine
import co.nimblehq.jsonapi.model.JsonApiException
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@ExperimentalCoroutinesApi
class NetworkClientTest {

    private val path = "user"
    private val request = HttpRequestBuilder()

    @BeforeTest
    fun setUp() {
        request.url("$BuildKonfig.BASE_URL$API_VERSION$path")
    }

    @Test
    fun `when calling fetch - it returns correct object`() = runTest {
        val engine = jsonMockEngine(NETWORK_MOCK_MODEL_RESULT, path)
        val networkClient = NetworkClient(engine = engine)
        networkClient
            .fetch<NetworkMockModel>(request)
            .test {
                awaitItem().title shouldBe "Hello"
                awaitComplete()
            }
    }

    @Test
    fun `when calling fetchWithMeta - it returns correct object`() = runTest {
        val engine = jsonMockEngine(NETWORK_META_MOCK_MODEL_RESULT, path)
        val networkClient = NetworkClient(engine = engine)
        networkClient
            .fetchWithMeta<NetworkMockModel, NetworkMetaMockModel>(request)
            .test {
                val response = awaitItem()
                response.first.title shouldBe "Hello"
                response.second.page shouldBe 1
                awaitComplete()
            }
    }

    @Test
    fun `When calling fetchEmpty - it returns correctly`() = runTest {
        val engine = jsonMockEngine("", path)
        val networkClient = NetworkClient(engine = engine)
        networkClient
            .fetchEmpty(request)
            .test {
                val response = awaitItem()
                response shouldBe Unit
                awaitComplete()
            }
    }

    @Test
    fun `when calling fetch with invalid path - it returns correct object`() = runTest {
        val engine = jsonMockEngine(NETWORK_MOCK_MODEL_RESULT, "")
        val networkClient = NetworkClient(engine = engine)
        networkClient
            .fetch<NetworkMockModel>(request)
            .test {
                when(val error = awaitError()) {
                    is JsonApiException -> error.errors.map { it.code } shouldContain "not_found"
                    else -> fail("Should not return incorrect error type")
                }
            }
    }
}
