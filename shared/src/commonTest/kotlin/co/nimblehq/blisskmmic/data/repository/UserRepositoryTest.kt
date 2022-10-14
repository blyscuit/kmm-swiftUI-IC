package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.repository.UserRepositoryImpl
import co.nimblehq.blisskmmic.domain.repository.UserRepository
import co.nimblehq.blisskmmic.helpers.json.ERROR_JSON_RESULT
import co.nimblehq.blisskmmic.helpers.json.LOG_IN_JSON_RESULT
import co.nimblehq.blisskmmic.helpers.mock.NetworkMockModel
import co.nimblehq.blisskmmic.helpers.mock.ktor.jsonMockEngine
import co.nimblehq.jsonapi.model.JsonApiException
import io.ktor.client.request.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    @Suppress("MaxLineLength")
    @Test
    fun `When calling login with success response, it returns correct object`() = runTest {
        val engine = jsonMockEngine(LOG_IN_JSON_RESULT)
        val networkClient = NetworkClient(engine = engine)
        val userRepository = UserRepositoryImpl(networkClient)
        userRepository
            .logIn("", "")
            .collect {
                assertEquals(it.refreshToken, "refresh_token")
            }
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling login with failure response, it returns correct error`() = runTest {
        val engine = jsonMockEngine(ERROR_JSON_RESULT)
        val networkClient = NetworkClient(engine = engine)
        val userRepository = UserRepositoryImpl(networkClient)
        userRepository
            .logIn("", "")
            .catch { error ->
                when(error) {
                    is JsonApiException -> assertTrue(error.errors.map { it.code }.contains("invalid_token"))
                    else -> fail("Should not return incorrect error type")
                }
            }
            .collect {
                fail("Should not return object")
            }
    }
}
