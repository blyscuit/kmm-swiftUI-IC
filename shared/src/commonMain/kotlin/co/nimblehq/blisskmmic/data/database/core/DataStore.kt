package co.nimblehq.blisskmmic.data.database.core

import co.nimblehq.blisskmmic.MR
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer

interface DataStore {

    fun <T> get(
        serializer: KSerializer<T>,
        key: String
    ): Flow<T>
    fun <T> save(
        serializer: KSerializer<T>,
        key: String,
        value: T
    )
    fun remove(key: String)
}

@OptIn(ExperimentalSettingsApi::class, ExperimentalSerializationApi:: class)
class DataStoreImpl(private val settings: Settings): DataStore {

    override fun <T> get(serializer: KSerializer<T>, key: String): Flow<T> {
        return flow {
            settings
                .decodeValueOrNull(serializer, key)
                ?.let { emit(it) }
                ?: error(MR.strings.common_error.toString())
        }
    }

    override fun <T> save(serializer: KSerializer<T>, key: String, value: T) {
        settings.encodeValue(serializer, key, value)
    }

    override fun remove(key: String) {
        settings.remove(key)
    }
}
