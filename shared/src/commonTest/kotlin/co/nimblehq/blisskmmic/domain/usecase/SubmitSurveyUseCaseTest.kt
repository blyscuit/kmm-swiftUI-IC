package co.nimblehq.blisskmmic.domain.usecase

import app.cash.turbine.test
import co.nimblehq.blisskmmic.domain.model.SurveySubmission
import co.nimblehq.blisskmmic.domain.model.fakeSurveySubmission
import co.nimblehq.blisskmmic.domain.repository.MockSurveyRepository
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesFakes
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@UsesMocks(SurveyRepository::class)
@UsesFakes(SurveySubmission::class)
@ExperimentalCoroutinesApi
class SubmitSurveyUseCaseTest {

    private val email = "email@mail.com"
    private val mocker = Mocker()
    private val surveyRepository = MockSurveyRepository(mocker)
    private val submitSurveyUseCase = SubmitSurveyUseCaseImpl(surveyRepository)
    private val submission = fakeSurveySubmission()

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling submit with a success response - it returns correct object`() = runTest {
        mocker.every {
            surveyRepository.submitSurvey(isAny())
        } returns flow { emit(Unit) }

        submitSurveyUseCase(submission)
            .test {
                awaitItem() shouldBe Unit
                awaitComplete()
            }
    }

    @Test
    fun `When calling reset with a failure response - it returns correct error`() = runTest {
        mocker.every {
            surveyRepository.submitSurvey(isAny())
        } returns flow { error("Fail") }

        submitSurveyUseCase(submission)
            .test {
                awaitError().message shouldBe "Fail"
            }
    }
}
