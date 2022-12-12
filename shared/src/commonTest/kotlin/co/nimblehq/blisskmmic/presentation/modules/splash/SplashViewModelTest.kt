package co.nimblehq.blisskmmic.presentation.modules.splash

import app.cash.turbine.test
import app.cash.turbine.testIn
import co.nimblehq.blisskmmic.MR
import co.nimblehq.blisskmmic.domain.usecase.CheckLoginUseCase
import co.nimblehq.blisskmmic.domain.usecase.ResetPasswordUseCase
import co.nimblehq.blisskmmic.presentation.modules.resetpassword.ResetPasswordViewModel
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest : TestsWithMocks() {

    @Mock
    lateinit var checkLoginUseCase: CheckLoginUseCase

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    
    private val splashViewModel by withMocks { SplashViewModel(checkLoginUseCase) }

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
    fun `When calling checkLogin with true response, it changes viewState correctly`() = runTest {
        mocker.every {
            checkLoginUseCase()
        } returns flowOf(true)

        splashViewModel.checkLogin()

        splashViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLogin shouldBe true
                result.isLoading shouldBe false
                cancel()
            }
    }

    @Test
    fun `When calling checkLogin with false response, it changes viewState correctly`() = runTest {
        mocker.every {
            checkLoginUseCase()
        } returns flow{
            delay(100)
            emit(false)
        }

        splashViewModel.checkLogin()

        splashViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLogin shouldBe false
                result.isLoading shouldBe false
                cancel()
            }
    }

    @Test
    fun `When calling checkLogin with faliure response, it changes viewState correctly`() = runTest {
        mocker.every {
            checkLoginUseCase()
        } returns flow {
            delay(100)
            error("Error")
        }

        splashViewModel.checkLogin()

        splashViewModel
            .viewState
            .test {
                awaitItem().isLoading shouldBe true
                val result = awaitItem()
                result.isLogin shouldBe false
                result.isLoading shouldBe false
                cancel()
            }
    }
}
