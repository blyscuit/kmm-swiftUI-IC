package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.data.model.ResetPasswordMeta
import co.nimblehq.blisskmmic.data.model.fakeResetPasswordMeta
import co.nimblehq.blisskmmic.domain.repository.MockAccountRecoveryRepository
import co.nimblehq.blisskmmic.domain.repository.AccountRecoveryRepository
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.kodein.mock.*
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@UsesMocks(AccountRecoveryRepository::class)
@UsesFakes(ResetPasswordMeta::class)
@ExperimentalCoroutinesApi
class ResetPasswordUseCaseTest {

    private val email = "email@mail.com"
    private val mocker = Mocker()
    private val accountRecoveryRepository = MockAccountRecoveryRepository(mocker)
    private val resetPasswordMeta = fakeResetPasswordMeta()
    private val resetPasswordUseCase = ResetPasswordUseCaseImpl(accountRecoveryRepository)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling reset with a success response, it returns correct object`() = runTest {
        mocker.every {
            accountRecoveryRepository.resetPasswordWith(email)
        } returns flow { emit(resetPasswordMeta) }

        resetPasswordUseCase(email)
            .collect{
                it shouldBe resetPasswordMeta.message
            }
    }

    @Test
    fun `When calling reset with a failure response, it returns correct error`() = runTest {
        mocker.every {
            accountRecoveryRepository.resetPasswordWith(email)
        } returns flow { error("Fail") }

        resetPasswordUseCase(email)
            .catch {
                it.message shouldBe "Fail"
            }
            .collect{
                fail("Should not receive object")
            }
    }
}
