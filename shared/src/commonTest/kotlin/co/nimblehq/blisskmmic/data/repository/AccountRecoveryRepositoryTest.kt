package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.model.ResetPasswordMeta
import co.nimblehq.blisskmmic.data.model.fakeResetPasswordMeta
import co.nimblehq.blisskmmic.data.network.datasource.MockNetworkDataSource
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesFakes
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@UsesMocks(NetworkDataSource::class)
@UsesFakes(ResetPasswordMeta::class)
@ExperimentalCoroutinesApi
class AccountRecoveryRepositoryTest {

    private val mocker = Mocker()
    private val networkDataSource = MockNetworkDataSource(mocker)
    private val resetPasswordMeta = fakeResetPasswordMeta()
    private val accountRecoveryRepository = AccountRecoveryRepositoryImpl(networkDataSource)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling reset with success response - it returns correct object`() = runTest {
        mocker.every {
            networkDataSource.resetPassword(isAny())
        } returns flow { emit(resetPasswordMeta) }
        accountRecoveryRepository
            .resetPasswordWith("")
            .collect {
                it.message shouldBe resetPasswordMeta.message
            }
    }

    @Test
    fun `When calling reset with failure response - it returns correct error`() = runTest {
        mocker.every {
            networkDataSource.resetPassword(isAny())
        } returns flow { error("Fail") }
        accountRecoveryRepository
            .resetPasswordWith("")
            .catch {
                it.message shouldBe "Fail"
            }
            .collect {
                fail("Should not return object")
            }
    }
}
