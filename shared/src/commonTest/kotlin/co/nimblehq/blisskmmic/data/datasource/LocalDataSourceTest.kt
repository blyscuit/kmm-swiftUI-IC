package co.nimblehq.blisskmmic.data.datasource

import co.nimblehq.blisskmmic.data.database.core.DataStore
import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSourceImpl
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class LocalDataSourceTest: TestsWithMocks() {

    @Mock
    lateinit var dataStore: DataStore
    @Fake
    lateinit var tokenDB: TokenDatabaseModel

    private val dataSource by withMocks { LocalDataSourceImpl(dataStore) }

    override fun setUpMocks() = injectMocks(mocker)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When saving token- it saves the correct key and value to dataStore`() = runTest {
        mocker.every {
            dataStore.save(
                TokenDatabaseModel.serializer(),
                LocalDataSourceImpl.DB_USER_SESSION_KEY,
                tokenDB
            )
        } returns Unit

        dataSource
            .save(tokenDB)

        mocker.verify {
            dataStore.save(
                TokenDatabaseModel.serializer(),
                LocalDataSourceImpl.DB_USER_SESSION_KEY,
                tokenDB
            )
        }
    }

    @Test
    fun `When calling getToken- dataStore returns correct value`() = runTest {
        mocker.every {
            dataStore.get(
                TokenDatabaseModel.serializer(),
                LocalDataSourceImpl.DB_USER_SESSION_KEY
            )
        } returns flow { emit(tokenDB) }

        dataSource
            .getToken()
            .collect {
                it shouldBe tokenDB
            }
    }
}
