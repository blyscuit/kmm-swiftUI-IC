package co.nimblehq.blisskmmic.data.repository

import app.cash.turbine.test
import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSource
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.domain.model.TokenApiModel
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@ExperimentalCoroutinesApi
class AuthenticationRepositoryTest: TestsWithMocks() {

    @Mock
    lateinit var localDataSource: LocalDataSource
    @Mock
    lateinit var networkDataSource: NetworkDataSource
    @Fake
    lateinit var token: TokenApiModel
    @Fake
    lateinit var tokenDB: TokenDatabaseModel

    private val authenticationRepository by withMocks { AuthenticationRepositoryImpl(networkDataSource, localDataSource) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling log in with success response, it returns correct object`() = runTest {
        mocker.every {
            networkDataSource.logIn(isAny())
        } returns flow { emit(token) }
        mocker.every {
            localDataSource.save(isAny())
        } returns Unit
        authenticationRepository
            .logIn("", "")
            .collect {
                it.refreshToken shouldBe token.refreshToken
            }
    }

    @Test
    fun `When calling log in with failure response, it returns correct error`() = runTest {
        mocker.every {
            networkDataSource.logIn(isAny())
        } returns flow { error("Fail") }
        authenticationRepository
            .logIn("", "")
            .catch {
                it.message shouldBe "Fail"
            }
            .collect {
                fail("Should not return object")
            }
    }

    @Test
    fun `When logging in successfully, it saves data to localDataSource`() = runTest {
        var saveCount = 0
        mocker.every {
            networkDataSource.logIn(isAny())
        } returns flow { emit(token) }
        mocker.every {
            localDataSource.save(isAny())
        } runs {
            saveCount++
            Unit
        }

        authenticationRepository.logIn("", "")
            .collect {
                saveCount shouldBe 1
            }
    }

    @Test
    fun `When saving a token, it stores the correct key and value to localDataSource`() = runTest {
        var saveCount = 0
        mocker.every {
            localDataSource.save(tokenDB)
        } runs {
            saveCount++
            Unit
        }

        authenticationRepository
            .save(tokenDB.toToken())

        saveCount shouldBe 1
    }

    @Test
    fun `When calling getToken, it returns the correct value`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns flow { emit(tokenDB) }

        authenticationRepository
            .getCachedToken()
            .collect {
                it shouldBe token.toToken()
            }
    }

    @Test
    fun `When calling hasCachedToken, it returns true when there is a token`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns flowOf(tokenDB)

        authenticationRepository
            .hasCachedToken()
            .test {
                awaitItem() shouldBe true
                awaitComplete()
            }
    }

    @Test
    fun `When calling hasCachedToken, it returns false when there is an error`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns flow { error("No token") }

        authenticationRepository
            .hasCachedToken()
            .test {
                awaitItem() shouldBe false
                awaitComplete()
            }
    }
}
