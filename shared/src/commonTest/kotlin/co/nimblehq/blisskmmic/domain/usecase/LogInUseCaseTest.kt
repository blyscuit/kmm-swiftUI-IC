package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.kodein.mock.*
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
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

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Suppress("MaxLineLength")
    @Test
    fun `Given LogInRepository returns success When LogInUseCase calls login Then it should return correct object`() = runTest {
        mocker.every {
            userRepository.logIn(email, password)
        } returns flow { emit(token) }

        logInUseCase(email, password)
            .collect{
                assertEquals(it.refreshToken, token.refreshToken)
            }
    }

    @Suppress("MaxLineLength")
    @Test
    fun `Given LogInRepository returns failure When LogInUseCase calls login Then it should return correct error`() = runTest {
        mocker.every {
            userRepository.logIn(email, password)
        } returns flow { error("Fail") }

        logInUseCase(email, password)
            .catch {
                assertEquals("Fail", it.message)
            }
            .collect{
                fail("Should not receive object")
            }
    }
    
    @Suppress("MaxLineLength")
    @Test
    fun `Given a LogInRepository When LogInUseCase calls login Then it should call repository with correct input`() = runTest {
        mocker.every {
            userRepository.logIn(email, password)
        } returns flow { emit(token) }

        logInUseCase(email, password)

        mocker.verifyWithSuspend {
            userRepository.logIn(email, password)
        }
    }
}
