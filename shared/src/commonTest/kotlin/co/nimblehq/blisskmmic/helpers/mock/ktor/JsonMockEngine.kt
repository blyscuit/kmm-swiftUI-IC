package co.nimblehq.blisskmmic.helpers.mock.ktor

import co.nimblehq.blisskmmic.helpers.json.NOT_FOUND_JSON_RESULT
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*

fun jsonMockEngine(
    jsonString: String,
    path: String,
    statusCode: HttpStatusCode = HttpStatusCode.OK
): MockEngine {
     fun MockRequestHandleScope.response(request: HttpRequestData): HttpResponseData {
        val responseHeader = headersOf(HttpHeaders.ContentType, "application/json")
        return if (apiPath(request.url.fullPath) == path) {
            respond(
                jsonString,
                statusCode,
                responseHeader
            )
        } else {
            respondError(
                HttpStatusCode.NotFound,
                NOT_FOUND_JSON_RESULT,
                responseHeader
            )
        }
    }

    return MockEngine { request ->
        response(request)
    }
}
