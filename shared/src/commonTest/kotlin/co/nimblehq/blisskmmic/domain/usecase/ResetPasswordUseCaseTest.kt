package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.data.model.ResetPasswordMeta
import co.nimblehq.blisskmmic.domain.repository.ResetPasswordRepository
import co.nimblehq.jsonapi.model.ApiJson
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@ExperimentalCoroutinesApi
class ResetPasswordUseCaseTest : TestsWithMocks() {

    private val email = "email@mail.com"

    @Mock
    lateinit var resetPasswordRepository: ResetPasswordRepository
    @Fake
    lateinit var resetPasswordMeta: ResetPasswordMeta

    val resetPasswordUseCase by withMocks { ResetPasswordUseCaseImpl(resetPasswordRepository) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling reset with a success response, it returns correct object`() = runTest {
        val result = ResetPasswordMeta("result")
        mocker.every {
            resetPasswordRepository.reset(email)
        } returns flow { emit(result) }

        resetPasswordUseCase(email)
            .collect{
                it shouldBe result.message
            }
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling reset with a failure response, it returns correct error`() = runTest {
        mocker.every {
            resetPasswordRepository.reset(email)
        } returns flow { error("Fail") }

        resetPasswordUseCase(email)
            .catch {
                it.message shouldBe "Fail"
            }
            .collect{
                fail("Should not receive object")
            }
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling reset, it calls resetPasswordRepository with correct inputs`() = runTest {
        mocker.every {
            resetPasswordRepository.reset(email)
        } returns flow { emit(resetPasswordMeta) }

        resetPasswordUseCase(email)

        mocker.verifyWithSuspend {
            resetPasswordRepository.reset(email)
        }
    }
}
