package co.nimblehq.blisskmmic.data.network.helpers

import co.nimblehq.blisskmmic.BuildKonfig
import io.ktor.client.request.*
import io.ktor.http.*

interface TargetType {

    val path: String
    val method: HttpMethod
    val body: Any?
    val headers: Map<String, String>?

    val requestBuilder: HttpRequestBuilder
    get() {
        val builder = HttpRequestBuilder()
        builder.url("$BuildKonfig.BASE_URL$API_VERSION$path")
        builder.method = method
        builder.contentType(ContentType.Application.Json)
        builder.setBody(body)
        return builder
    }
}
