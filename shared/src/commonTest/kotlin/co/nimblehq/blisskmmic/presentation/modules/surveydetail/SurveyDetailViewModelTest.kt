package co.nimblehq.blisskmmic.presentation.modules.surveydetail

import app.cash.turbine.test
import co.nimblehq.blisskmmic.domain.model.SurveyDetail
import co.nimblehq.blisskmmic.domain.usecase.GetSurveyDetailUseCase
import co.nimblehq.blisskmmic.helpers.flow.delayFlowOf
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SurveyDetailViewModelTest: TestsWithMocks() {

    @Mock
    lateinit var getSurveyDetailUseCase: GetSurveyDetailUseCase
    @Fake
    lateinit var survey: SurveyDetail

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val surveyDetailViewModel by withMocks { SurveyDetailViewModel(getSurveyDetailUseCase) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        mocker.reset()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `When calling getDetail before setId - it does not trigger getSurveyDetailUseCase`() = runTest {
        mocker.every {
            getSurveyDetailUseCase(isAny())
        } returns flowOf(survey)

        surveyDetailViewModel.getDetail()

        verify {}
    }

    @Test
    fun `When calling getDetail with success response - it changes viewState to success with correct item`() = runTest {
        surveyDetailViewModel.setSurveyId("")

        mocker.every {
            getSurveyDetailUseCase(isAny())
        } returns delayFlowOf(survey)

        surveyDetailViewModel.getDetail()

        surveyDetailViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLoading shouldBe false
                result.surveyDetail?.title shouldBe survey.title
                cancel()
            }
    }

    @Test
    fun `When calling showQuestion with existing detail - it changes viewState with correct item`() = runTest {
        surveyDetailViewModel.setSurveyId("")

        mocker.every {
            getSurveyDetailUseCase(isAny())
        } returns delayFlowOf(survey)

        surveyDetailViewModel.getDetail()

        surveyDetailViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLoading shouldBe false
                result.surveyDetail?.title shouldBe survey.title
                surveyDetailViewModel.showQuestion()
                awaitItem().isShowingQuestion shouldBe true
                cancel()
            }
    }

    @Test
    fun `When calling showQuestion with no detail - it should call getDetail`() = runTest {
        surveyDetailViewModel.setSurveyId("")

        mocker.every {
            getSurveyDetailUseCase(isAny())
        } returns delayFlowOf(survey)

        surveyDetailViewModel.showQuestion()

        surveyDetailViewModel
            .viewState
            .test {
                awaitItem().isShowingQuestion shouldBe true
                val result = awaitItem()
                result.isLoading shouldBe false
                result.surveyDetail?.title shouldBe survey.title
                result.isShowingQuestion shouldBe true
                cancel()
            }
    }

    @Test
    fun `When calling getDetail with faliure response - it changes viewState to error`() = runTest {
        surveyDetailViewModel.setSurveyId("")

        val errorMessage = "Test Error"
        mocker.every {
            getSurveyDetailUseCase(isAny())
        } returns delayFlowOf(errorMessage)

        surveyDetailViewModel.getDetail()

        surveyDetailViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLoading shouldBe false
                result.error shouldBe errorMessage
                cancel()
            }
    }
}
