package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.PaginationMeta
import co.nimblehq.blisskmmic.domain.model.Survey
import co.nimblehq.blisskmmic.domain.model.fakePaginationMeta
import co.nimblehq.blisskmmic.domain.model.fakeSurvey
import co.nimblehq.blisskmmic.domain.repository.MockSurveyRepository
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesFakes
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@OptIn(ExperimentalCoroutinesApi::class)
@UsesMocks(SurveyRepository::class)
@UsesFakes(Survey::class, PaginationMeta::class)
class SurveyListUseCaseTest {

    private val mocker = Mocker()
    private val surveyRepository = MockSurveyRepository(mocker)
    private val survey = fakeSurvey()
    private val paginationMeta = fakePaginationMeta()
    private val surveyListUseCase = SurveyListUseCaseImpl(surveyRepository)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling surveyList with a success response, it returns correct object`() = runTest {
        mocker.every {
            surveyRepository.survey(1)
        } returns flow { emit(Pair(listOf(survey), paginationMeta)) }

        surveyListUseCase(1)
            .collect{
                it.first().title shouldBe survey.title
            }
    }

    @Test
    fun `When calling surveyList with a failure response, it returns correct error`() = runTest {
        mocker.every {
            surveyRepository.survey(1)
        } returns flow { error("Fail") }

        surveyListUseCase(1)
            .catch {
                it.message shouldBe "Fail"
            }
            .collect{
                fail("Should not receive object")
            }
    }
}
