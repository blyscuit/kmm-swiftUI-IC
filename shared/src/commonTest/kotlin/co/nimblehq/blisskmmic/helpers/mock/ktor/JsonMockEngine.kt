package co.nimblehq.blisskmmic.helpers.mock.ktor

import co.nimblehq.blisskmmic.helpers.mock.NETWORK_MOCK_MODEL_RESULT
import io.ktor.client.engine.mock.*
import io.ktor.http.*

fun jsonMockEngine(
    jsonString: String,
    statusCode: HttpStatusCode = HttpStatusCode.OK
): MockEngine {
    return MockEngine { _ ->
        respond(
            content = jsonString,
            status = statusCode,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }
}
