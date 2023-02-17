@file:Suppress("MagicNumber")
package co.nimblehq.blisskmmic.presentation.modules.surveydetail

import app.cash.turbine.test
import app.cash.turbine.testIn
import co.nimblehq.blisskmmic.domain.model.SurveyDetail
import co.nimblehq.blisskmmic.domain.usecase.GetSurveyDetailUseCase
import co.nimblehq.blisskmmic.domain.usecase.SubmitSurveyUseCase
import co.nimblehq.blisskmmic.helpers.flow.delayFlowOf
import co.nimblehq.blisskmmic.presentation.model.SurveySubmissionUiModel
import co.nimblehq.blisskmmic.presentation.model.toSurveySubmission
import co.nimblehq.blisskmmic.presentation.models.surveyDetailDummy
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
    @Mock
    lateinit var submitSurveyUseCase: SubmitSurveyUseCase
    @Fake
    lateinit var survey: SurveyDetail
    @Fake
    lateinit var answer: SurveySubmissionUiModel.Answer

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val surveyDetailViewModel by withMocks {
        SurveyDetailViewModel(getSurveyDetailUseCase, submitSurveyUseCase)
    }

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
    fun `When calling getDetail with failure response - it changes viewState to error`() = runTest {
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

    @Test
    fun `When calling addAnswer - it changes questionViewState to the correct item`() = runTest {
        surveyDetailViewModel.setSurveyId("")

        mocker.every {
            getSurveyDetailUseCase(isAny())
        } returns delayFlowOf(surveyDetailDummy(3))

        surveyDetailViewModel.getDetail()

        val viewState = surveyDetailViewModel.viewState.testIn(backgroundScope)
        val questionViewState = surveyDetailViewModel.questionViewState.testIn(backgroundScope)
        viewState.skipItems(1)
        viewState.awaitItem()
        surveyDetailViewModel.addAnswer(listOf(answer))
        questionViewState.skipItems(1)
        questionViewState.awaitItem().currentQuestionIndex shouldBe 1
        surveyDetailViewModel.addAnswer(listOf(answer))
        questionViewState.awaitItem().currentQuestionIndex shouldBe 2
        surveyDetailViewModel.addAnswer(listOf(answer))
        val lastState = questionViewState.awaitItem()
        lastState.currentQuestionIndex shouldBe 3
        lastState.isShowingSubmit shouldBe true
        viewState.cancelAndIgnoreRemainingEvents()
        questionViewState.cancelAndIgnoreRemainingEvents()
    }

    @Test
    fun `When calling submitAnswer with success response - it calls submitSurveyUseCase with correct arguments and returns correct values`() = runTest {
        surveyDetailViewModel.setSurveyId("")

        val submissions = listOf(SurveySubmissionUiModel("", listOf(answer)))

        mocker.every {
            getSurveyDetailUseCase(isAny())
        } returns delayFlowOf(surveyDetailDummy(3))
        mocker.every {
            submitSurveyUseCase(submissions.toSurveySubmission(""))
        } returns delayFlowOf(Unit)

        surveyDetailViewModel.getDetail()

        val viewState = surveyDetailViewModel.viewState.testIn(backgroundScope)
        val questionViewState = surveyDetailViewModel.questionViewState.testIn(backgroundScope)
        viewState.skipItems(1)
        viewState.awaitItem()
        surveyDetailViewModel.addAnswer(listOf(answer))
        questionViewState.skipItems(2)
        surveyDetailViewModel.submitAnswer()
        questionViewState.awaitItem().isLoading shouldBe true
        questionViewState.awaitItem().isShowingSuccess shouldBe true
        viewState.cancelAndIgnoreRemainingEvents()
        questionViewState.cancelAndIgnoreRemainingEvents()
    }

    @Test
    fun `When calling submitAnswer with error response - it returns correct error`() = runTest {
        surveyDetailViewModel.setSurveyId("")

        val errorMessage = "Test Error"

        mocker.every {
            getSurveyDetailUseCase(isAny())
        } returns delayFlowOf(surveyDetailDummy(3))
        mocker.every {
            submitSurveyUseCase(isAny())
        } returns delayFlowOf(errorMessage)

        surveyDetailViewModel.getDetail()

        val viewState = surveyDetailViewModel.viewState.testIn(backgroundScope)
        val questionViewState = surveyDetailViewModel.questionViewState.testIn(backgroundScope)
        viewState.skipItems(1)
        viewState.awaitItem()
        surveyDetailViewModel.addAnswer(listOf(answer))
        questionViewState.skipItems(2)
        surveyDetailViewModel.submitAnswer()
        questionViewState.awaitItem().isLoading shouldBe true
        questionViewState.awaitItem().isShowingSuccess shouldBe false
        viewState.cancelAndIgnoreRemainingEvents()
        questionViewState.cancelAndIgnoreRemainingEvents()
    }
}
