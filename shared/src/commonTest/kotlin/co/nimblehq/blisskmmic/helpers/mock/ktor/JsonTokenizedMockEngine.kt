package co.nimblehq.blisskmmic.helpers.mock.ktor

import co.nimblehq.blisskmmic.helpers.json.NOT_FOUND_JSON_RESULT
import co.nimblehq.blisskmmic.helpers.json.UNAUTHORIZED_JSON_RESULT
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*

fun jsonTokenizedMockEngine(
    jsonString: String,
    accessToken: String?,
    path: String,
    statusCode: HttpStatusCode = HttpStatusCode.OK
): MockEngine {
    fun MockRequestHandleScope.response(
        request: HttpRequestData
    ): HttpResponseData {
        val responseHeader = headersOf(HttpHeaders.ContentType, "application/json")
        val auth = request.headers["Authorization"].toString()
        return if (apiPath(request.url.fullPath) == path) {
            return if (auth == "Bearer $accessToken") {
                respond(
                    jsonString,
                    statusCode,
                    responseHeader
                )
            } else {
                respondError(
                    HttpStatusCode.Unauthorized,
                    UNAUTHORIZED_JSON_RESULT,
                    responseHeader
                )
            }
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
