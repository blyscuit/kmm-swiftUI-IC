package co.nimblehq.blisskmmic.presentation.modules.login

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCase
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
class LoginViewModelTest : TestsWithMocks() {

    private val email = "email@mail.com"
    private val password = "pass"
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var logInUseCase: LogInUseCase
    @Fake
    lateinit var token: Token

    private val loginViewModel by withMocks { LoginViewModel(logInUseCase) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `When calling log in with success response, it changes viewState to success`() = runTest {
        mocker.every {
            logInUseCase(email, password)
        } returns flow { emit(token) }

        loginViewModel.logIn(email, password)

        val result = loginViewModel
            .viewState
            .first { it.isSuccess }
        result.isSuccess shouldBe true
        result.isLoading shouldBe false
    }

    @Test
    fun `When calling log in with faliure response, it changes viewState to error`() = runTest {
        val errorMessage = "Test Error"
        mocker.every {
            logInUseCase(email, password)
        } returns flow { error(errorMessage) }

        loginViewModel.logIn(email, password)

        val result = loginViewModel
            .viewState
            .first { it.error != null }
        errorMessage shouldBe result.error
        result.isLoading shouldBe false
    }
}
