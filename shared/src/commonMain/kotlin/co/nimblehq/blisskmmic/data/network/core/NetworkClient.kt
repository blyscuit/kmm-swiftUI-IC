package co.nimblehq.blisskmmic.data.network.core

import co.nimblehq.jsonapi.json.JsonApi
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class NetworkClient {

    val client: HttpClient

    val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    constructor(engine: HttpClientEngine? = null) {
        if (engine == null) {
            client = HttpClient() {
                install(Logging)
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
            }
        } else {
            client = HttpClient(engine) {
                install(Logging)
                install(ContentNegotiation) {
                    json(json)
                }
            }
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
}
