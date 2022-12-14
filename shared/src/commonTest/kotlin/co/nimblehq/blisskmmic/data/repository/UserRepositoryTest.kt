package co.nimblehq.blisskmmic.data.repository

import app.cash.turbine.test
import co.nimblehq.blisskmmic.data.model.UserApiModel
import co.nimblehq.blisskmmic.data.model.fakeUserApiModel
import co.nimblehq.blisskmmic.data.network.datasource.MockNetworkDataSource
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
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

@UsesMocks(NetworkDataSource::class)
@UsesFakes(UserApiModel::class)
@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private val mocker = Mocker()
    private val networkDataSource = MockNetworkDataSource(mocker)
    private val user = fakeUserApiModel()
    private val userRepository = UserRepositoryImpl(networkDataSource)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling getProfile with success response- it returns correct object`() = runTest {
        mocker.every {
            networkDataSource.profile(isAny())
        } returns flowOf(user)
        userRepository
            .getProfile()
            .test {
                val item = awaitItem()
                item.name shouldBe user.name
                item.avatarUrl shouldBe user.avatarUrl
                awaitComplete()
            }
    }

    @Test
    fun `When calling getProfile with failure response- it returns correct error`() = runTest {
        mocker.every {
            networkDataSource.profile(isAny())
        } returns flow { error("Fail") }
        userRepository
            .getProfile()
            .test {
                awaitError().message shouldBe "Fail"
            }
    }
}
