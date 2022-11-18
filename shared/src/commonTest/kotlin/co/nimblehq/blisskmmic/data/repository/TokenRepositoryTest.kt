package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSource
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import co.nimblehq.blisskmmic.data.database.model.toToken
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.domain.model.TokenApiModel
import co.nimblehq.blisskmmic.domain.model.toToken
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
    lateinit var localDataSource: LocalDataSource
    @Mock
    lateinit var networkDataSource: NetworkDataSource
    @Fake
    lateinit var token: TokenApiModel
    @Fake
    lateinit var tokenDB: TokenDatabaseModel

    private val tokenRepository by withMocks { TokenRepositoryImpl(networkDataSource, localDataSource) }

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
        tokenRepository
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
        tokenRepository
            .logIn("", "")
            .catch {
                it.message shouldBe "Fail"
            }
            .collect {
                fail("Should not return object")
            }
    }

    @Test
    fun `When calling log in with a success response, it calls localDataSource to save`() = runTest {
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

        tokenRepository.logIn("", "")
            .collect {
                saveCount shouldBe 1
            }
    }

    @Test
    fun `When calling save, it calls localDataSource save with correct key and value`() = runTest {
        var saveCount = 0
        mocker.every {
            localDataSource.save(tokenDB)
        } runs {
            saveCount++
            Unit
        }

        tokenRepository
            .save(tokenDB.toToken())

        saveCount shouldBe 1
    }

    @Test
    fun `When calling getToken, localDataSource returns with correct value`() = runTest {
        mocker.every {
            localDataSource.getToken()
        } returns flow { emit(tokenDB) }

        tokenRepository
            .getCachedToken()
            .collect {
                it shouldBe token.toToken()
            }
    }
}
