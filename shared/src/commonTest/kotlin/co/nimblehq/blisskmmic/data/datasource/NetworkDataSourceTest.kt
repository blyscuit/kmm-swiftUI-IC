package co.nimblehq.blisskmmic.data.datasource

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSourceImpl
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.data.network.target.ResetPasswordTargetType
import co.nimblehq.blisskmmic.helpers.json.ERROR_JSON_RESULT
import co.nimblehq.blisskmmic.helpers.json.LOG_IN_JSON_RESULT
import co.nimblehq.blisskmmic.helpers.json.RESET_PASSWORD_JSON_RESULT
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
class NetworkDataSourceTest {

    // Log in

    @Test
    fun `When calling log in with success response, it returns correct object`() = runTest {
        val engine = jsonMockEngine(LOG_IN_JSON_RESULT, "oauth/token")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .logIn(LoginTargetType("", ""))
            .collect {
                it.refreshToken shouldBe "refresh_token"
            }
    }

    @Test
    fun `When calling log in with failure response, it returns correct error`() = runTest {
        val engine = jsonMockEngine(ERROR_JSON_RESULT, "oauth/token")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .logIn(LoginTargetType("", ""))
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

    // Reset password

    @Test
    fun `When calling reset password with success response, it returns correct object`() = runTest {
        val engine = jsonMockEngine(RESET_PASSWORD_JSON_RESULT, "passwords")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .resetPassword(ResetPasswordTargetType(""))
            .collect {
                it.message shouldBe """
                    If your email address exists in our database, you will receive a password recovery link at your email address in a few minutes.
                """.trimIndent()
            }
    }
}
