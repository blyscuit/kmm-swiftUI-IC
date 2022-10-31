package co.nimblehq.blisskmmic.data.service

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.service.ApiServiceImpl
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.helpers.json.ERROR_JSON_RESULT
import co.nimblehq.blisskmmic.helpers.json.LOG_IN_JSON_RESULT
import co.nimblehq.blisskmmic.helpers.mock.ktor.jsonMockEngine
import co.nimblehq.jsonapi.model.JsonApiException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

@ExperimentalCoroutinesApi
class ApiServiceTest {

    @Test
    fun `When calling log in with success response, it returns correct object`() = runTest {
        val engine = jsonMockEngine(LOG_IN_JSON_RESULT)
        val networkClient = NetworkClient(engine = engine)
        val service = ApiServiceImpl(networkClient)
        service
            .logIn(LoginTargetType("", ""))
            .collect {
                assertEquals(it.refreshToken, "refresh_token")
            }
    }

    @Test
    fun `When calling log in with failure response, it returns correct error`() = runTest {
        val engine = jsonMockEngine(ERROR_JSON_RESULT)
        val networkClient = NetworkClient(engine = engine)
        val service = ApiServiceImpl(networkClient)
        service
            .logIn(LoginTargetType("", ""))
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
