package co.nimblehq.blisskmmic.data.network.core

import app.cash.turbine.test
import co.nimblehq.blisskmmic.BuildKonfig
import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSource
import co.nimblehq.blisskmmic.data.database.datasource.MockLocalDataSource
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import co.nimblehq.blisskmmic.data.network.helpers.API_VERSION
import co.nimblehq.blisskmmic.helpers.mock.NETWORK_META_MOCK_MODEL_RESULT
import co.nimblehq.blisskmmic.helpers.mock.NETWORK_MOCK_MODEL_RESULT
import co.nimblehq.blisskmmic.helpers.mock.NetworkMetaMockModel
import co.nimblehq.blisskmmic.helpers.mock.NetworkMockModel
import co.nimblehq.blisskmmic.helpers.mock.ktor.jsonTokenizedMockEngine
import co.nimblehq.jsonapi.model.JsonApiException
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@ExperimentalCoroutinesApi
@UsesMocks(LocalDataSource::class)
class TokenizedNetworkClientTest {

    private val mocker = Mocker()
    private val localDataSource = MockLocalDataSource(mocker)

    private val token = TokenDatabaseModel(
        accessToken = "Access",
        tokenType = "",
        expiresIn = 1,
        refreshToken = "",
        createdAt = 1
    )
    private val path = "user"
    private val request = HttpRequestBuilder()

    @BeforeTest
    fun setUp() {
        mocker.reset()
        request.url("$BuildKonfig.BASE_URL$API_VERSION$path")
    }

    @Test
    fun `when calling fetch - it returns correct object`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns flowOf(token)
        val engine = jsonTokenizedMockEngine(
            NETWORK_MOCK_MODEL_RESULT,
            token.accessToken,
            path
        )
        val networkClient = TokenizedNetworkClient(engine = engine, localDataSource)
        networkClient
            .fetch<NetworkMockModel>(request)
            .test {
                awaitItem().title shouldBe "Hello"
                awaitComplete()
            }
    }

    @Test
    fun `when calling fetchWithMeta - it returns correct object`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns flowOf(token)
        val engine = jsonTokenizedMockEngine(
            NETWORK_META_MOCK_MODEL_RESULT,
            token.accessToken,
            path
        )
        val networkClient = TokenizedNetworkClient(engine = engine, localDataSource)
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
    fun `when calling fetch with incorrect token - it returns correct error`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns flowOf(token)
        val engine = jsonTokenizedMockEngine(
            NETWORK_MOCK_MODEL_RESULT,
            "no access",
            path
        )
        val networkClient = TokenizedNetworkClient(engine = engine, localDataSource)
        networkClient
            .fetch<NetworkMockModel>(request)
            .test {
                when(val error = awaitError()) {
                    is JsonApiException -> error.errors.map { it.code } shouldContain "unauthorized"
                    else -> fail("Should not return incorrect error type")
                }
            }
    }
}
