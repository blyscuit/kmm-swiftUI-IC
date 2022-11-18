package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.model.PaginationMetaApiModel
import co.nimblehq.blisskmmic.data.model.SurveyApiModel
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@ExperimentalCoroutinesApi
class SurveyRepositoryTest: TestsWithMocks() {

    @Mock
    lateinit var networkDataSource: NetworkDataSource
    @Fake
    lateinit var survey: SurveyApiModel
    @Fake
    lateinit var meta: PaginationMetaApiModel

    private val surveyRepository by withMocks { SurveyRepositoryImpl(networkDataSource) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling survey with success response, it returns correct object`() = runTest {
        mocker.every {
            networkDataSource.survey(isAny())
        } returns flow { emit(Pair(listOf(survey), meta)) }
        surveyRepository
            .survey(1)
            .collect {
                it.first.first().title shouldBe survey.title
                it.second.page shouldBe meta.page
            }
    }

    @Test
    fun `When calling survey with failure response, it returns correct error`() = runTest {
        mocker.every {
            networkDataSource.survey(isAny())
        } returns flow { error("Fail") }
        surveyRepository
            .survey(1)
            .catch {
                it.message shouldBe "Fail"
            }
            .collect {
                fail("Should not return object")
            }
    }
}
