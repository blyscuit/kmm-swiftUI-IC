package co.nimblehq.blisskmmic.data.network.helpers

import co.nimblehq.blisskmmic.BuildKonfig
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

interface TargetType<T> {

    val path: String
    val method: HttpMethod
    val body: T?
}

internal inline fun <reified T> TargetType<T>.requestBuilder(): HttpRequestBuilder {
    val builder = HttpRequestBuilder()
    builder.url("${BuildKonfig.BASE_URL}$API_VERSION$path")
    builder.method = method
    builder.contentType(ContentType.Application.Json)
    if (method == HttpMethod.Get) {
        builder.setQueryParameters(body)
    } else {
        builder.setBody(body)
    }
    return builder
}

private inline fun <reified T> HttpRequestBuilder.setQueryParameters(parameters: T?) {
    parameters?.run {
        val queryParameters = Json.encodeToJsonElement(this).jsonObject
        for ((key, value) in queryParameters) {
            parameter(key, value)
        }
    }
}
