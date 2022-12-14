package co.nimblehq.blisskmmic.data.datastore.core

import co.nimblehq.blisskmmic.MR
import co.nimblehq.blisskmmic.data.database.core.DataStoreImpl
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import org.kodein.mock.Fake
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

@OptIn(ExperimentalSettingsApi::class, ExperimentalSerializationApi:: class)
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
    fun `When saving a token- it sets settings with the correct key and value`() = runTest {
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
    fun `When retrieving the token- it gets the correct key and value from settings`() = runTest {
        settings.encodeValue(TokenDatabaseModel.serializer(), testKey, token)

        val dataStore = DataStoreImpl(settings)

        dataStore.get(TokenDatabaseModel.serializer(), testKey)
            .collect {
                it shouldBe token
            }
    }

    @Test
    fun `When getting a key with no saved value- it returns the correct error`() = runTest {
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
