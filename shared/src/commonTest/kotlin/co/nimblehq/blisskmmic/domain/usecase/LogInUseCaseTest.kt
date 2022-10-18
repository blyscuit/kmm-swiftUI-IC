package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.repository.TokenRepository
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.kodein.mock.*
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@ExperimentalCoroutinesApi
class LogInUseCaseTest : TestsWithMocks() {

    private val email = "email@mail.com"
    private val password = "pass"

    @Mock
    lateinit var tokenRepository: TokenRepository
    @Fake
    lateinit var token: Token

    val logInUseCase by withMocks { LogInUseCaseImpl(tokenRepository) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling login with a success response, it returns correct object`() = runTest {
        mocker.every {
            tokenRepository.logIn(email, password)
        } returns flow { emit(token) }

        logInUseCase(email, password)
            .collect{
                it.refreshToken shouldBe token.refreshToken
            }
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling login with a failure response, it returns correct error`() = runTest {
        mocker.every {
            tokenRepository.logIn(email, password)
        } returns flow { error("Fail") }

        logInUseCase(email, password)
            .catch {
                it.message shouldBe "Fail"
            }
            .collect{
                fail("Should not receive object")
            }
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling login, it calls userRepository with correct inputs`() = runTest {
        mocker.every {
            tokenRepository.logIn(email, password)
        } returns flow { emit(token) }

        logInUseCase(email, password)

        mocker.verifyWithSuspend {
            tokenRepository.logIn(email, password)
        }
    }
}
