package co.nimblehq.blisskmmic.data.datasource

import app.cash.turbine.test
import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSourceImpl
import co.nimblehq.blisskmmic.data.network.target.*
import co.nimblehq.blisskmmic.domain.model.SurveySubmission
import co.nimblehq.blisskmmic.helpers.json.*
import co.nimblehq.blisskmmic.helpers.mock.ktor.jsonMockEngine
import co.nimblehq.jsonapi.model.JsonApiException
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.fail

private const val CORRECT_SURVEY_PAGE_SIZE = 12

@ExperimentalCoroutinesApi
class NetworkDataSourceTest {

    // Log in

    @Test
    fun `When calling log in with success response - it returns correct object`() = runTest {
        val engine = jsonMockEngine(LOG_IN_JSON_RESULT, "oauth/token")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .logIn(LoginTargetType("", ""))
            .test {
                awaitItem().refreshToken shouldBe "refresh_token"
                awaitComplete()
            }
    }

    @Test
    fun `When calling log in with failure response - it returns correct error`() = runTest {
        val engine = jsonMockEngine(ERROR_JSON_RESULT, "oauth/token")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .logIn(LoginTargetType("", ""))
            .test {
                when(val error = awaitError()) {
                    is JsonApiException -> error.errors.map { it.code } shouldContain "invalid_token"
                    else -> fail("Should not return incorrect error type")
                }
            }
    }

    // Reset password

    @Test
    fun `When calling reset password with success response - it returns correct object`() = runTest {
        val engine = jsonMockEngine(RESET_PASSWORD_JSON_RESULT, "passwords")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .resetPassword(ResetPasswordTargetType(""))
            .test {
                awaitItem().message shouldBe """
                    If your email address exists in our database, you will receive a password recovery link at your email address in a few minutes.
                """.trimIndent()
                awaitComplete()
            }
    }

    // Survey

    @Test
    fun `When calling survey with success response - it returns correct object`() = runTest {
        val engine = jsonMockEngine(SURVEY_LIST_JSON_RESULT, "surveys")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .survey(SurveySelectionTargetType())
            .test {
                val response = awaitItem()
                response .first.size shouldBe 2
                response .first.first().title shouldBe "Scarlett Bangkok"
                response .second.page shouldBe 1
                awaitComplete()
            }
    }

    @Test
    fun `When calling survey with failure response - it returns correct error`() = runTest {
        val engine = jsonMockEngine(ERROR_JSON_RESULT, "surveys")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .survey(SurveySelectionTargetType())
            .test {
                when (val error = awaitError()) {
                    is JsonApiException -> error.errors.map { it.code } shouldContain "invalid_token"
                    else -> fail("Should not return incorrect error type")
                }
            }
    }

    // Profile

    @Test
    fun `When calling profile with success response - it returns correct object`() = runTest {
        val engine = jsonMockEngine(USER_PROFILE_JSON_RESULT, "me")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .profile(UserProfileTargetType())
            .test {
                val response = awaitItem()
                response.email shouldBe "mail@mail.com"
                response.name shouldBe "Name"
                awaitComplete()
            }
    }

    // Refresh Token

    @Test
    fun `When calling refresh token with success response - it returns correct object`() = runTest {
        val engine = jsonMockEngine(LOG_IN_JSON_RESULT, "oauth/token")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .refreshToken(RefreshTokenType(""))
            .test {
                awaitItem().refreshToken shouldBe "refresh_token"
                awaitComplete()
            }
    }

    // Survey Detail

    @Test
    fun `When calling survey detail with success response - it returns correct object`() = runTest {
        val id = "ABC"
        val engine = jsonMockEngine(SURVEY_DETAIL_JSON_RESULT, "surveys/${id}")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .surveyDetail(SurveyDetailTargetType(id))
            .test {
                val response = awaitItem()
                response.title shouldBe "Scarlett Bangkok"
                response.questions.size shouldBe CORRECT_SURVEY_PAGE_SIZE
                awaitComplete()
            }
    }

    // Submit Survey

    @Test
    fun `When calling submit survey with success response - it returns correct object`() = runTest {
        val submission = SurveySubmission("", listOf())
        val engine = jsonMockEngine("", "responses")
        val networkClient = NetworkClient(engine = engine)
        val dataSource = NetworkDataSourceImpl(networkClient)
        dataSource
            .submitSurvey(SubmitSurveyTargetType(submission))
            .test {
                val response = awaitItem()
                response shouldBe Unit
                awaitComplete()
            }
    }
}
