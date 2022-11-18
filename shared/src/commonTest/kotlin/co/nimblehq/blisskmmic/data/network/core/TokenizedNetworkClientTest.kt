package co.nimblehq.blisskmmic.data.network.core

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
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
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
        "Access",
        "",
        1,
        "",
        1
    )
    private val path = "user"
    private val request = HttpRequestBuilder()

    @BeforeTest
    fun setUp() {
        mocker.reset()
        request.url("$BuildKonfig.BASE_URL$API_VERSION$path")
    }

    @Test
    fun `when calling fetch, it returns correct object`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns flow { emit(token) }
        val engine = jsonTokenizedMockEngine(
            NETWORK_MOCK_MODEL_RESULT,
            token.accessToken,
            path
        )
        val networkClient = TokenizedNetworkClient(engine = engine, localDataSource)
        networkClient
            .fetch<NetworkMockModel>(request)
            .collect {
                it.title shouldBe "Hello"
            }
    }

    @Test
    fun `when calling fetchWithMeta, it returns correct object`() = runTest {
        val mocker = Mocker()
        val localDataSource = MockLocalDataSource(mocker)
        mocker.every {
            localDataSource.getToken()
        } returns flow { emit(token) }
        val engine = jsonTokenizedMockEngine(
            NETWORK_META_MOCK_MODEL_RESULT,
            token.accessToken,
            path
        )
        val networkClient = TokenizedNetworkClient(engine = engine, localDataSource)
        networkClient
            .fetchWithMeta<NetworkMockModel, NetworkMetaMockModel>(request)
            .collect {
                it.first.title shouldBe "Hello"
                it.second.page shouldBe 1
            }
    }

    @Test
    fun `when calling fetch with incorrect token, it returns correct error`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns flow { emit(token) }
        val engine = jsonTokenizedMockEngine(
            NETWORK_MOCK_MODEL_RESULT,
            "no access",
            path
        )
        val networkClient = TokenizedNetworkClient(engine = engine, localDataSource)
        networkClient
            .fetch<NetworkMockModel>(request)
            .catch { error ->
                when(error) {
                    is JsonApiException -> error.errors.map { it.code } shouldContain "unauthorized"
                    else -> fail("Should not return incorrect error type")
                }
            }
            .collect {
                fail("Should not return object")
            }
    }
}
