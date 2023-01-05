package co.nimblehq.blisskmmic.domain.usecase

import app.cash.turbine.test
import co.nimblehq.blisskmmic.domain.model.PaginationMeta
import co.nimblehq.blisskmmic.domain.model.Survey
import co.nimblehq.blisskmmic.domain.model.fakePaginationMeta
import co.nimblehq.blisskmmic.domain.model.fakeSurvey
import co.nimblehq.blisskmmic.domain.repository.AuthenticationRepository
import co.nimblehq.blisskmmic.domain.repository.MockAuthenticationRepository
import co.nimblehq.blisskmmic.domain.repository.MockSurveyRepository
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesFakes
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
@UsesMocks(AuthenticationRepository::class)
class LogOutUseCaseTest {

    private val mocker = Mocker()
    private val authenticationRepository = MockAuthenticationRepository(mocker)
    private val logOutUseCase = LogOutUseCaseImpl(authenticationRepository)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling surveyList with a success response - it returns correct object`() = runTest {
        mocker.every {
            authenticationRepository.logOut()
        } returns flowOf(Unit)

        logOutUseCase()
            .test {
                awaitItem() shouldNotBe null
                awaitComplete()
            }
    }

    @Test
    fun `When calling surveyList with a failure response - it returns correct error`() = runTest {
        mocker.every {
            authenticationRepository.logOut()
        } returns flow {
            error("Fail")
        }

        logOutUseCase()
            .test {
                awaitError().message shouldBe "Fail"
            }
    }
}
