package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.repository.TokenRepositoryImpl
import co.nimblehq.blisskmmic.helpers.json.ERROR_JSON_RESULT
import co.nimblehq.blisskmmic.helpers.json.LOG_IN_JSON_RESULT
import co.nimblehq.blisskmmic.helpers.mock.ktor.jsonMockEngine
import co.nimblehq.jsonapi.model.JsonApiException
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.fail

@ExperimentalCoroutinesApi
class TokenRepositoryTest {

    @Suppress("MaxLineLength")
    @Test
    fun `When calling login with success response, it returns correct object`() = runTest {
        val engine = jsonMockEngine(LOG_IN_JSON_RESULT)
        val networkClient = NetworkClient(engine = engine)
        val tokenRepository = TokenRepositoryImpl(networkClient)
        tokenRepository
            .logIn("", "")
            .collect {
                it.refreshToken shouldBe "refresh_token"
            }
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling login with failure response, it returns correct error`() = runTest {
        val engine = jsonMockEngine(ERROR_JSON_RESULT)
        val networkClient = NetworkClient(engine = engine)
        val tokenRepository = TokenRepositoryImpl(networkClient)
        tokenRepository
            .logIn("", "")
            .catch { error ->
                when(error) {
                    is JsonApiException -> error.errors.map { it.code } shouldContain "invalid_token"
                    else -> fail("Should not return incorrect error type")
                }
            }
            .collect {
                fail("Should not return object")
            }
    }
}
