package co.nimblehq.blisskmmic.data.datastore.core

import co.nimblehq.blisskmmic.MR
import co.nimblehq.blisskmmic.data.database.core.DataStoreImpl
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@ExperimentalCoroutinesApi
class DataStoreTest : TestsWithMocks() {

    @Fake
    lateinit var token: TokenDatabaseModel

    override fun setUpMocks() = injectMocks(mocker)

    private val testKey = "testKey"

    private var settings = MapSettings()

    @BeforeTest
    fun setUp() {
        settings = MapSettings()
        mocker.reset()
    }

    @Test
    fun `When calling save, it sets settings with correct key and value`() = runTest {
        val dataStore = DataStoreImpl(settings)
        dataStore.save(
            TokenDatabaseModel.serializer(),
            testKey,
            token
        )
        settings
            .decodeValueOrNull(
                TokenDatabaseModel.serializer(),
                testKey
            ) shouldBe token
    }

    @Test
    fun `When calling get, it gets settings with correct key and value`() = runTest {
        settings.encodeValue(TokenDatabaseModel.serializer(), testKey, token)

        val dataStore = DataStoreImpl(settings)

        dataStore.get(TokenDatabaseModel.serializer(), testKey)
            .collect {
                it shouldBe token
            }
    }

    @Test
    fun `When calling get with no saved value, it returns correct error`() = runTest {
        val dataStore = DataStoreImpl(settings)

        dataStore.get(TokenDatabaseModel.serializer(), testKey)
            .catch {
                it.message shouldBe MR.strings.common_error.toString()
            }
            .collect {
                fail("Should not return value")
            }
    }
}
