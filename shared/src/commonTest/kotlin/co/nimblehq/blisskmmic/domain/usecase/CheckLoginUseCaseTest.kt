package co.nimblehq.blisskmmic.domain.usecase

import app.cash.turbine.test
import co.nimblehq.blisskmmic.domain.repository.AuthenticationRepository
import co.nimblehq.blisskmmic.domain.repository.MockAuthenticationRepository
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@UsesMocks(AuthenticationRepository::class)
@ExperimentalCoroutinesApi
class CheckLoginUseCaseTest {

    private val mocker = Mocker()
    private val authenticationRepository = MockAuthenticationRepository(mocker)
    private val checkLoginUseCase = CheckLoginUseCaseImpl(authenticationRepository)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling check with response true - it returns true`() = runTest {
        mocker.every {
            authenticationRepository.hasCachedToken()
        } returns flowOf(true)

        checkLoginUseCase()
            .test {
                awaitItem() shouldBe true
                awaitComplete()
            }
    }

    @Test
    fun `When calling check with response false - it returns false`() = runTest {
        mocker.every {
            authenticationRepository.hasCachedToken()
        } returns flowOf(false)

        checkLoginUseCase()
            .test {
                awaitItem() shouldBe false
                awaitComplete()
            }
    }

    @Test
    fun `When calling check with a failure response - it returns correct error`() = runTest {
        mocker.every {
            authenticationRepository.hasCachedToken()
        } returns flow { error("Fail") }

        checkLoginUseCase()
            .test {
                awaitError().message shouldBe "Fail"
            }
    }
}
