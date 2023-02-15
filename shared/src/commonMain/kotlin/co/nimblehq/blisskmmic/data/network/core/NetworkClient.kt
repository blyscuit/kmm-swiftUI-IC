package co.nimblehq.blisskmmic.data.network.core

import co.nimblehq.jsonapi.json.JsonApi
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.github.aakira.napier.LogLevel.DEBUG
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.logging.LogLevel.ALL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

open class NetworkClient {

    val client: HttpClient

    val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    constructor(engine: HttpClientEngine? = null) {
        Napier.takeLogarithm()
        Napier.base(DebugAntilog())
        client = if (engine == null) {
            HttpClient(clientConfig())
        } else {
            HttpClient(engine, clientConfig())
        }
    }

    @Suppress("EmptySecondaryConstructor")
    constructor() : this(null) {}

    inline fun <reified T> fetch(builder: HttpRequestBuilder) : Flow<T> {
        return flow {
            val body = client.request(builder).bodyAsText()
            val data = JsonApi(json).decodeFromJsonApiString<T>(body)
            emit(data)
        }
    }

    inline fun <reified T, reified M> fetchWithMeta(builder: HttpRequestBuilder) : Flow<Pair<T, M>> {
        return flow {
            val body = client.request(builder).bodyAsText()
            val data = JsonApi(json).decodeWithMetaFromJsonApiString<T, M>(body)
            emit(data)
        }
    }

    inline fun fetchEmpty(builder: HttpRequestBuilder) : Flow<Unit> {
        return flow {
            client.request(builder).bodyAsText()
            emit(Unit)
        }
    }

    open fun clientConfig(): HttpClientConfig<*>.() -> Unit {
        return {
            install(Logging) {
                loggingConfig()
            }
            install(ContentNegotiation) {
                json(json)
            }
        }
    }

    fun loggingConfig(): Logging.Config.() -> Unit {
        return {
            level = ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.log(DEBUG, message = message)
                }
            }
        }
    }
}
