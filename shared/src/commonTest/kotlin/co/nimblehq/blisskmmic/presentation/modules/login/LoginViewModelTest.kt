package co.nimblehq.blisskmmic.presentation.modules.login

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.model.fakeToken
import co.nimblehq.blisskmmic.domain.repository.MockUserRepository
import co.nimblehq.blisskmmic.domain.repository.UserRepository
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCase
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.kodein.mock.*
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

@ExperimentalCoroutinesApi
class LoginViewModelTest : TestsWithMocks() {

    private val email = "email@mail.com"
    private val password = "pass"

    @Mock
    lateinit var logInUseCase: LogInUseCase
    @Fake
    lateinit var token: Token

    val loginViewModel by withMocks { LoginViewModel(logInUseCase) }

    override fun setUpMocks() = injectMocks(mocker)

    @Test
    fun test_LogInViewModel_LogIn_ShouldReturnCorrectObject() = runTest {
        mocker.every {
            logInUseCase(email, password)
        } returns flow { emit(token) }

        loginViewModel
            .login(email, password)
            .collect{
                assertEquals(it.refreshToken, token.refreshToken)
            }
    }
}
