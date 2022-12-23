package co.nimblehq.blisskmmic.presentation.modules.account

import app.cash.turbine.test
import co.nimblehq.blisskmmic.domain.usecase.LogOutUseCase
import co.nimblehq.blisskmmic.helpers.flow.delayFlowOf
import co.nimblehq.blisskmmic.presentation.model.AccountUiModel
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import org.koin.core.context.stopKoin
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class AccountViewModelTest : TestsWithMocks() {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var logOutUseCase: LogOutUseCase
    @Fake
    lateinit var account: AccountUiModel

    private val accountViewModel by withMocks { AccountViewModel(account, logOutUseCase) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        mocker.reset()
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `When init - it has correct viewState`() = runTest {
        accountViewModel
            .viewState
            .test {
                awaitItem().accountUiModel shouldBe account
                cancel()
            }
    }

    @Test
    fun `When calling log out with success response - it changes viewState to logout`() = runTest {
        mocker.every {
            logOutUseCase()
        } returns delayFlowOf(Unit)

        accountViewModel.logOut()

        accountViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLoading shouldBe false
                result.isLogout shouldBe true
                cancel()
            }
    }

    @Test
    fun `When calling log out with error response - it changes viewState to no logout`() = runTest {
        mocker.every {
            logOutUseCase()
        } returns delayFlowOf("Fail")

        accountViewModel.logOut()

        accountViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLoading shouldBe false
                result.isLogout shouldBe false
                cancel()
            }
    }
}
