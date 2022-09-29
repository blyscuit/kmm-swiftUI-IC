package co.nimblehq.blisskmmic.data.network.helpers

import co.nimblehq.jsonapi.model.JsonApiException

fun Throwable.jsonApiException() : String? {
    when(this) {
        is JsonApiException -> return this.errors.first().detail
        else -> return this.message
    }
}
