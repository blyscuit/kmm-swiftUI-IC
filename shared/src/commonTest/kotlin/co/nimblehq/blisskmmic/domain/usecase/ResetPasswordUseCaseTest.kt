package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.repository.ResetPasswordRepository
import co.nimblehq.jsonapi.model.ApiJson
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
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

    val resetPasswordUseCase by withMocks { ResetPasswordUseCaseImpl(resetPasswordRepository) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling reset with a success response, it returns correct object`() = runTest {
        var result = "result"
        var apiJsonMap = mapOf("message" to ApiJson.string(result))
        var apiJson = ApiJson.nested(apiJsonMap)
        mocker.every {
            resetPasswordRepository.reset(email)
        } returns flow { emit(apiJson) }

        resetPasswordUseCase(email)
            .collect{
                it shouldBe result
            }
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling reset with a wrong type response, it returns correct error`() = runTest {
        var apiJsonMap = mapOf("" to ApiJson.int(0))
        var apiJson = ApiJson.nested(apiJsonMap)
        mocker.every {
            resetPasswordRepository.reset(email)
        } returns flow { emit(apiJson) }

        resetPasswordUseCase(email)
            .catch {
                it shouldNotBe null
            }
            .collect{
                fail("Should not receive object")
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
        var result = "result"
        var apiJsonMap = mapOf("message" to ApiJson.string(result))
        var apiJson = ApiJson.nested(apiJsonMap)
        mocker.every {
            resetPasswordRepository.reset(email)
        } returns flow { emit(apiJson) }

        resetPasswordUseCase(email)

        mocker.verifyWithSuspend {
            resetPasswordRepository.reset(email)
        }
    }
}
