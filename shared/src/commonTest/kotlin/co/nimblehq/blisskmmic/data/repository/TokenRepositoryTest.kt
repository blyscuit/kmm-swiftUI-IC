package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.network.repository.TokenRepositoryImpl
import co.nimblehq.blisskmmic.data.network.service.ApiService
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.domain.model.TokenApiModel
import io.kotest.matchers.shouldBe
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
class TokenRepositoryTest: TestsWithMocks() {

    @Mock
    lateinit var service: ApiService
    @Fake
    lateinit var token: TokenApiModel

    private val tokenRepository by withMocks { TokenRepositoryImpl(service) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling log in with success response, it returns correct object`() = runTest {
        mocker.every {
            service.logIn(isAny())
        } returns flow { emit(token) }
        tokenRepository
            .logIn("", "")
            .collect {
                it.refreshToken shouldBe token.refreshToken
            }
    }

    @Test
    fun `When calling log in with failure response, it returns correct error`() = runTest {
        mocker.every {
            service.logIn(isAny())
        } returns flow { error("Fail") }
        tokenRepository
            .logIn("", "")
            .catch {
                it.message shouldBe "Fail"
            }
            .collect {
                fail("Should not return object")
            }
    }
}
