package co.nimblehq.blisskmmic.presentation.modules.resetpassword

import co.nimblehq.blisskmmic.domain.usecase.ResetPasswordUseCase
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class ResetPasswordViewModelTest : TestsWithMocks() {

    private val email = "email@mail.com"
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val resultMessage = "result"

    @Mock
    lateinit var resetPasswordUseCase: ResetPasswordUseCase

    private val resetPasswordViewModel by withMocks { ResetPasswordViewModel(resetPasswordUseCase) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling reset with success response, it changes viewState to success`() = runTest {
        mocker.every {
            resetPasswordUseCase(email)
        } returns flow { emit(resultMessage) }

        resetPasswordViewModel.reset(email)

        val result = resetPasswordViewModel
            .viewState
            .first { it.successResponse != null }
        result.successResponse shouldBe resultMessage
        result.isLoading shouldBe false
    }

    @Suppress("MaxLineLength")
    @Test
    fun `When calling reset with faliure response, it changes viewState to error`() = runTest {
        val errorMessage = "Test Error"
        mocker.every {
            resetPasswordUseCase(email)
        } returns flow { error(errorMessage) }

        mocker.every {
            resetPasswordUseCase(email)
        } returns flow { emit(resultMessage) }

        resetPasswordViewModel.reset(email)

        val result = resetPasswordViewModel
            .viewState
            .first { it.error != null }
        errorMessage shouldBe result.error
        result.isLoading shouldBe false
    }
}
