package co.nimblehq.blisskmmic.data.network.core

import co.nimblehq.jsonapi.json.JsonApi
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
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
        if (engine == null) {
            client = HttpClient(clientConfig())
        } else {
            client = HttpClient(engine, clientConfig())
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

    private fun clientConfig(): HttpClientConfig<*>.() -> Unit {
        return {
            install(Logging)
            install(ContentNegotiation) {
                json(json)
            }
            install(Auth) {
                bearer(bearer())
            }
        }
    }

    open fun bearer(): BearerAuthConfig.() -> Unit {
        return {}
    }
}
