package co.nimblehq.blisskmmic.data.network.helpers

import io.ktor.client.request.*
import io.ktor.http.*

interface TargetType {

    var baseURL: String
    var path: String
    var method: HttpMethod
    var body: Any?
    var headers: Map<String, String>?

    val requestBuilder: HttpRequestBuilder
    get() {
        val builder = HttpRequestBuilder()
        builder.url("$baseURL$path")
        builder.method = method
        builder.contentType(ContentType.Application.Json)
        builder.setBody(body)
        return builder
    }
}
