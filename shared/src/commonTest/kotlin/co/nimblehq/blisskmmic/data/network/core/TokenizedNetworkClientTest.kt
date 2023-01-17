package co.nimblehq.blisskmmic.data.network.core

import app.cash.turbine.test
import co.nimblehq.blisskmmic.BuildKonfig
import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSource
import co.nimblehq.blisskmmic.data.database.datasource.MockLocalDataSource
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import co.nimblehq.blisskmmic.data.network.datasource.MockNetworkDataSource
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.data.network.helpers.API_VERSION
import co.nimblehq.blisskmmic.domain.model.TokenApiModel
import co.nimblehq.blisskmmic.helpers.flow.delayFlowOf
import co.nimblehq.blisskmmic.helpers.mock.NETWORK_META_MOCK_MODEL_RESULT
import co.nimblehq.blisskmmic.helpers.mock.NETWORK_MOCK_MODEL_RESULT
import co.nimblehq.blisskmmic.helpers.mock.NetworkMetaMockModel
import co.nimblehq.blisskmmic.helpers.mock.NetworkMockModel
import co.nimblehq.blisskmmic.helpers.mock.ktor.jsonTokenizedMockEngine
import co.nimblehq.jsonapi.model.JsonApiException
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@ExperimentalCoroutinesApi
@UsesMocks(LocalDataSource::class, NetworkDataSource::class)
class TokenizedNetworkClientTest {

    private val mocker = Mocker()
    private val localDataSource = MockLocalDataSource(mocker)
    private val networkDataSource = MockNetworkDataSource(mocker)

    private val token = TokenDatabaseModel(
        accessToken = "Access",
        tokenType = "",
        expiresIn = 1,
        refreshToken = "",
        createdAt = 1
    )
    private val refreshedToken = TokenApiModel(
        accessToken = "Refreshed Access",
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
        mocker.every {
            networkDataSource.refreshToken(isAny())
        } returns delayFlowOf(refreshedToken)
        mocker.every {
            localDataSource.save(isAny())
        } returns Unit
    }

    @Test
    fun `when calling fetch - it returns correct object`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns flowOf(token)
        val engine = jsonTokenizedMockEngine(
            NETWORK_MOCK_MODEL_RESULT,
            refreshedToken.accessToken,
            path
        )
        val networkClient = TokenizedNetworkClient(engine, localDataSource, networkDataSource)
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
            refreshedToken.accessToken,
            path
        )
        val networkClient = TokenizedNetworkClient(engine, localDataSource, networkDataSource)
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
        val networkClient = TokenizedNetworkClient(engine, localDataSource, networkDataSource)
        networkClient
            .fetch<NetworkMockModel>(request)
            .test {
                when(val error = awaitError()) {
                    is JsonApiException -> error.errors.map { it.code } shouldContain "unauthorized"
                    else -> fail("Should not return incorrect error type")
                }
            }
    }

    @Test
    fun `when receiving expired token - it calls refresh token`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns delayFlowOf(token)
        var attempt = 0
        val engine = MockEngine { _ ->
            if(attempt == 0) {
                attempt++
                respond(
                    NETWORK_MOCK_MODEL_RESULT,
                    HttpStatusCode.Unauthorized,
                    headersOf(HttpHeaders.ContentType, "application/json")
                )
            } else {
                respond(
                    NETWORK_MOCK_MODEL_RESULT,
                    HttpStatusCode.OK,
                    headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        }
        val networkClient = TokenizedNetworkClient(engine, localDataSource, networkDataSource)
        networkClient
            .fetch<NetworkMockModel>(request)
            .test {
                awaitItem().title shouldBe "Hello"
                awaitComplete()
            }
    }
}
