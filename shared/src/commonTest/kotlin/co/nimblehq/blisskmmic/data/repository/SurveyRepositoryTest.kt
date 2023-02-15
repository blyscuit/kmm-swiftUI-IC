package co.nimblehq.blisskmmic.data.repository

import app.cash.turbine.test
import co.nimblehq.blisskmmic.data.model.PaginationMetaApiModel
import co.nimblehq.blisskmmic.data.model.SurveyApiModel
import co.nimblehq.blisskmmic.data.model.SurveyDetailApiModel
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.domain.model.SurveySubmission
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SurveyRepositoryTest: TestsWithMocks() {

    @Mock
    lateinit var networkDataSource: NetworkDataSource
    @Fake
    lateinit var survey: SurveyApiModel
    @Fake
    lateinit var meta: PaginationMetaApiModel
    @Fake
    lateinit var surveyDetail: SurveyDetailApiModel
    @Fake
    lateinit var submission: SurveySubmission

    private val surveyRepository by withMocks { SurveyRepositoryImpl(networkDataSource) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    // Get Survey

    @Test
    fun `When calling survey with success response - it returns correct object`() = runTest {
        mocker.every {
            networkDataSource.survey(isAny())
        } returns flowOf(Pair(listOf(survey), meta))
        surveyRepository
            .getSurvey(1)
            .test {
                val response = awaitItem()
                response.first.first().title shouldBe survey.title
                response.second.page shouldBe meta.page
                awaitComplete()
            }
    }

    @Test
    fun `When calling survey with failure response - it returns correct error`() = runTest {
        mocker.every {
            networkDataSource.survey(isAny())
        } returns flow { error("Fail") }
        surveyRepository
            .getSurvey(1)
            .test {
                awaitError().message shouldBe "Fail"
            }
    }

    // Get Survey Detail

    @Test
    fun `When calling surveyDetail with success response - it returns correct object`() = runTest {
        mocker.every {
            networkDataSource.surveyDetail(isAny())
        } returns flowOf(surveyDetail)
        surveyRepository
            .getSurveyDetail("")
            .test {
                val response = awaitItem()
                response.title shouldBe surveyDetail.title
                response.questions.size shouldBe surveyDetail.questions.size
                awaitComplete()
            }
    }

    // Submit Survey

    @Test
    fun `When calling submit survey with success response - it returns correct object`() = runTest {
        mocker.every {
            networkDataSource.submitSurvey(isAny())
        } returns flowOf(Unit)
        surveyRepository
            .submitSurvey(submission)
            .test {
                val response = awaitItem()
                response shouldBe Unit
                awaitComplete()
            }
    }

    @Test
    fun `When calling submit survey with failure response - it returns correct error`() = runTest {
        mocker.every {
            networkDataSource.submitSurvey(isAny())
        } returns flow { error("Fail") }
        surveyRepository
            .submitSurvey(submission)
            .test {
                awaitError().message shouldBe "Fail"
            }
    }
}
