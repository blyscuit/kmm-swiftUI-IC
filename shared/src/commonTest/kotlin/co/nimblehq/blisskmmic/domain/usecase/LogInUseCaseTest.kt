package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.repository.UserRepository
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
class LogInUseCaseTest : TestsWithMocks() {

    private val email = "email@mail.com"
    private val password = "pass"

    @Mock
    lateinit var userRepository: UserRepository
    @Fake
    lateinit var token: Token

    val logInUseCase by withMocks { LogInUseCaseImpl(userRepository) }

    override fun setUpMocks() = injectMocks(mocker)

    @Test
    fun test_LogInUseCase_LogIn_ShouldReturnCorrectObject() = runTest {
        mocker.every {
            userRepository.logIn(email, password)
        } returns flow { emit(token) }

        logInUseCase(email, password)
            .collect{
                assertEquals(it.refreshToken, token.refreshToken)
            }
    }

    @Test
    fun test_LogInUseCase_LogInFail_ShouldReturnCorrectError() = runTest {
        mocker.every {
            userRepository.logIn(email, password)
        } returns flow { throw error("Fail") }

        logInUseCase(email, password)
            .catch {
                assertEquals("Fail", it.message)
            }
            .collect{
                fail("Should not receive object")
            }
    }

    @Test
    fun test_LogInUseCase_LogIn_CallRepositoryWithCorrectInput() = runTest {
        mocker.every {
            userRepository.logIn(email, password)
        } returns flow { emit(token) }

        logInUseCase(email, password)

        mocker.verifyWithSuspend {
            userRepository.logIn(email, password)
        }
    }
}
