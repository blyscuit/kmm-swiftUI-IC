package co.nimblehq.blisskmmic.domain.usecase

import app.cash.turbine.test
import co.nimblehq.blisskmmic.domain.model.PaginationMeta
import co.nimblehq.blisskmmic.domain.model.Survey
import co.nimblehq.blisskmmic.domain.model.User
import co.nimblehq.blisskmmic.domain.model.fakeUser
import co.nimblehq.blisskmmic.domain.repository.MockUserRepository
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import co.nimblehq.blisskmmic.domain.repository.UserRepository
import io.kotest.matchers.shouldBe
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
@UsesMocks(UserRepository::class)
@UsesFakes(User::class)
class GetProfileUseCaseTest {

    private val mocker = Mocker()
    private val userRepository = MockUserRepository(mocker)
    private val user = fakeUser()
    private val getProfileUseCase = GetProfileUseCaseImpl(userRepository)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling getProfile with a success response, it returns correct object`() = runTest {
        mocker.every {
            userRepository.getProfile()
        } returns flowOf(user)

        getProfileUseCase()
            .test {
                val item = awaitItem()
                item.name shouldBe user.name
                item.avatarUrl shouldBe user.avatarUrl
                awaitComplete()
            }
    }

    @Test
    fun `When calling getProfile with a failure response, it returns correct error`() = runTest {
        mocker.every {
            userRepository.getProfile()
        } returns flow { error("Fail") }

        getProfileUseCase()
            .test {
                awaitError().message shouldBe "Fail"
            }
    }
}
