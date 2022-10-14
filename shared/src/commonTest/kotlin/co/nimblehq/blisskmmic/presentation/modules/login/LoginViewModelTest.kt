package co.nimblehq.blisskmmic.presentation.modules.login

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.model.fakeToken
import co.nimblehq.blisskmmic.domain.repository.MockUserRepository
import co.nimblehq.blisskmmic.domain.repository.UserRepository
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCase
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCaseImpl
import co.nimblehq.jsonapi.model.JsonApiError
import co.nimblehq.jsonapi.model.JsonApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.kodein.mock.*
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.*

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

    @Suppress("MaxLineLength")
    @Test
    fun `When calling login with success response, it changes viewState to success`() = runTest {
        mocker.every {
            logInUseCase(email, password)
        } returns flow { emit(token) }

        loginViewModel.login(email, password)

        val result = loginViewModel
            .viewState
            .first { it.isSuccess }
        assertTrue(result.isSuccess)
        assertFalse(result.isLoading)
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling login with faliure response, it changes viewState to error`() = runTest {
        val errorMessage = "Test Error"
        mocker.every {
            logInUseCase(email, password)
        } returns flow { error(errorMessage) }

        loginViewModel.login(email, password)

        val result = loginViewModel
            .viewState
            .first { it.error != null }
        assertEquals(errorMessage, result.error)
        assertFalse(result.isLoading)
    }
}
