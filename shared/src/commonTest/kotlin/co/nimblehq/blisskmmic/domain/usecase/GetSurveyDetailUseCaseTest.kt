package co.nimblehq.blisskmmic.domain.usecase

import app.cash.turbine.test
import co.nimblehq.blisskmmic.domain.model.*
import co.nimblehq.blisskmmic.domain.model.fakeSurveyDetail
import co.nimblehq.blisskmmic.domain.repository.MockSurveyRepository
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesFakes
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
@UsesMocks(SurveyRepository::class)
@UsesFakes(SurveyDetail::class)
class GetSurveyDetailUseCaseTest {

    private val mocker = Mocker()
    private val surveyRepository = MockSurveyRepository(mocker)
    private val survey = fakeSurveyDetail()
    private val getSurveyDetailUseCase = GetSurveyDetailUseCaseImpl(surveyRepository)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling getSurveyDetail with a success response - it returns correct object`() = runTest {
        mocker.every {
            getSurveyDetailUseCase(isAny())
        } returns flowOf(survey)

        getSurveyDetailUseCase("")
            .test {
                awaitItem().title shouldBe survey.title
                awaitComplete()
            }
    }

    @Test
    fun `When calling getSurveyDetail with a failure response - it returns correct error`() = runTest {
        mocker.every {
            getSurveyDetailUseCase(isAny())
        } returns flow { error("Fail") }

        getSurveyDetailUseCase("")
            .test {
                awaitError().message shouldBe "Fail"
            }
    }
}
