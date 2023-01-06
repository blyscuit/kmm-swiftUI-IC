@file:Suppress("MagicNumber")
package co.nimblehq.blisskmmic.presentation.modules.surveyselection

import app.cash.turbine.test
import co.nimblehq.blisskmmic.MR
import co.nimblehq.blisskmmic.domain.model.AppVersion
import co.nimblehq.blisskmmic.domain.model.Survey
import co.nimblehq.blisskmmic.domain.model.User
import co.nimblehq.blisskmmic.domain.platform.datetime.DateTimeFormatter
import co.nimblehq.blisskmmic.domain.usecase.GetAppVersionUseCase
import co.nimblehq.blisskmmic.domain.usecase.GetCurrentDateUseCase
import co.nimblehq.blisskmmic.domain.usecase.GetProfileUseCase
import co.nimblehq.blisskmmic.domain.usecase.SurveyListUseCase
import co.nimblehq.blisskmmic.helpers.flow.delayFlowOf
import co.nimblehq.blisskmmic.presentation.model.SurveyUiModel
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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

private const val TIME = 1L

@ExperimentalCoroutinesApi
class SurveySelectionViewModelTest : TestsWithMocks() {

    @Mock
    lateinit var getCurrentDateUseCase: GetCurrentDateUseCase
    @Mock
    lateinit var getProfileUseCase: GetProfileUseCase
    @Mock
    lateinit var getAppVersionUseCase: GetAppVersionUseCase
    @Mock
    lateinit var surveyListUseCase: SurveyListUseCase
    @Mock
    lateinit var dateTimeFormatter: DateTimeFormatter
    @Fake
    lateinit var user: User
    @Fake
    lateinit var appVersion: AppVersion
    @Fake
    lateinit var survey: Survey

    private val dateResult = "dateResult"
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val surveySelectionViewModel by withMocks {
        SurveySelectionViewModel(
            getCurrentDateUseCase,
            getProfileUseCase,
            getAppVersionUseCase,
            surveyListUseCase,
            dateTimeFormatter
        )
    }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        mocker.reset()
        mocker.every {
            dateTimeFormatter.getFormattedString(isAny(), isAny())
        } returns dateResult
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `When calling fetch with all successes - it changes viewState with correct item`() = runTest {
        mocker.every {
            getCurrentDateUseCase()
        } returns delayFlowOf(TIME)
        mocker.every {
            getProfileUseCase()
        } returns flowOf(user)
        mocker.every {
            getAppVersionUseCase()
        } returns flowOf(appVersion)
        mocker.every {
            surveyListUseCase(isAny())
        } returns flowOf(listOf(survey))

        surveySelectionViewModel.fetch()

        surveySelectionViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLoading shouldBe false
                result.surveyHeaderUiModel?.dateText shouldBe dateResult
                result.surveyHeaderUiModel?.todayText shouldBe MR.strings.common_today
                result.surveyHeaderUiModel?.imageUrl shouldBe user.avatarUrl
                result.accountUiModel?.appVersion shouldBe "v${appVersion.appVersion} (${appVersion.buildNumber})"
                result.accountUiModel?.name shouldBe user.avatarUrl
                awaitItem().surveys.firstOrNull()?.imageUrl shouldBe survey.imageUrl
                cancel()
            }
    }

    @Test
    fun `When calling fetch with fail date and success user version - it changes viewState with correct item`() = runTest {
        mocker.every {
            getCurrentDateUseCase()
        } returns delayFlowOf("")
        mocker.every {
            getProfileUseCase()
        } returns flowOf(user)
        mocker.every {
            getAppVersionUseCase()
        } returns flowOf(appVersion)
        mocker.every {
            surveyListUseCase(isAny())
        } returns flowOf(listOf(survey))

        surveySelectionViewModel.fetch()

        surveySelectionViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLoading shouldBe false
                result.surveyHeaderUiModel?.dateText shouldBe ""
                result.surveyHeaderUiModel?.todayText shouldBe MR.strings.common_today
                result.surveyHeaderUiModel?.imageUrl shouldBe user.avatarUrl
                cancelAndIgnoreRemainingEvents()
            }
    }

    @Test
    fun `When calling fetch with success date version and fail user - it changes viewState with correct item`() = runTest {
        mocker.every {
            getCurrentDateUseCase()

        } returns flowOf(TIME)
        mocker.every {
            getProfileUseCase()
        } returns delayFlowOf("")
        mocker.every {
            getAppVersionUseCase()
        } returns flowOf(appVersion)
        mocker.every {
            surveyListUseCase(isAny())
        } returns flowOf(listOf(survey))

        surveySelectionViewModel.fetch()

        surveySelectionViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLoading shouldBe false
                result.surveyHeaderUiModel?.dateText shouldBe dateResult
                result.surveyHeaderUiModel?.todayText shouldBe MR.strings.common_today
                result.surveyHeaderUiModel?.imageUrl shouldBe null
                result.accountUiModel?.avatarUrl shouldBe null
                result.accountUiModel?.name shouldBe ""
                cancelAndIgnoreRemainingEvents()
            }
    }

    @Test
    fun `When calling fetch with fail date user and version - it changes viewState to success with correct item`() = runTest {
        mocker.every {
            getCurrentDateUseCase()
        } returns flow { error("") }
        mocker.every {
            getProfileUseCase()
        } returns delayFlowOf("")
        mocker.every {
            getAppVersionUseCase()
        } returns flow { error("") }
        mocker.every {
            surveyListUseCase(isAny())
        } returns flowOf(listOf(survey))

        surveySelectionViewModel.fetch()

        surveySelectionViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLoading shouldBe false
                result.surveyHeaderUiModel?.dateText shouldBe ""
                result.surveyHeaderUiModel?.todayText shouldBe MR.strings.common_today
                result.surveyHeaderUiModel?.imageUrl shouldBe null
                result.accountUiModel?.appVersion shouldBe "()"
                cancelAndIgnoreRemainingEvents()
            }
    }

    @Test
    fun `When calling checkFetchMore fetch correct index - it changes viewState with correct item`() = runTest {
        mocker.every {
            getCurrentDateUseCase()
        } returns delayFlowOf(TIME)
        mocker.every {
            getProfileUseCase()
        } returns flowOf(user)
        mocker.every {
            getAppVersionUseCase()
        } returns flowOf(appVersion)
        mocker.every {
            surveyListUseCase(1)
        } returns flowOf(listOf(survey))
        mocker.every {
            surveyListUseCase(2)
        } returns delayFlowOf((listOf(survey, survey)))

        surveySelectionViewModel.fetch()

        surveySelectionViewModel
            .viewState
            .test {
                skipItems(3)
                surveySelectionViewModel.checkFetchMore(1)
                awaitItem().surveys.size shouldBe 3
                cancel()
            }
    }

    @Test
    fun `When calling checkFetchMore fetch incompleted index - it does not fetch more`() = runTest {
        mocker.every {
            getCurrentDateUseCase()
        } returns delayFlowOf(TIME)
        mocker.every {
            getProfileUseCase()
        } returns flowOf(user)
        mocker.every {
            getAppVersionUseCase()
        } returns flowOf(appVersion)
        mocker.every {
            surveyListUseCase(1)
        } returns flowOf(listOf(survey))

        surveySelectionViewModel.fetch()

        surveySelectionViewModel
            .viewState
            .test {
                skipItems(2)
                surveySelectionViewModel.checkFetchMore(-3)
                expectMostRecentItem().surveys.size shouldBe 1
            }
    }


    @Test
    fun `When calling checkFetchMore - currentSurvey returns correct item`() = runTest {
        val secondSurvey = Survey("second", "", "", "")
        mocker.every {
            getCurrentDateUseCase()
        } returns delayFlowOf(TIME)
        mocker.every {
            getProfileUseCase()
        } returns flowOf(user)
        mocker.every {
            getAppVersionUseCase()
        } returns flowOf(appVersion)
        mocker.every {
            surveyListUseCase(isAny())
        } returns flowOf(listOf(survey, secondSurvey))

        surveySelectionViewModel.fetch()

        surveySelectionViewModel
            .viewState
            .test {
                skipItems(2)
                surveySelectionViewModel.currentSurvey?.id shouldBe survey.id
                surveySelectionViewModel.checkFetchMore(1)
                surveySelectionViewModel.currentSurvey?.id shouldBe secondSurvey.id
                surveySelectionViewModel.checkFetchMore(0)
                surveySelectionViewModel.currentSurvey?.id shouldBe survey.id
                cancelAndIgnoreRemainingEvents()
            }
    }
}
