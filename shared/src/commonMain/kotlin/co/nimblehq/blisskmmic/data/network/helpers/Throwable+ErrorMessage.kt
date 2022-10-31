package co.nimblehq.blisskmmic.data.network.helpers

import co.nimblehq.jsonapi.model.JsonApiException

fun Throwable.toErrorMessage() : String? {
    return when(this) {
        is JsonApiException -> this.errors.first().detail
        else -> this.message
    }
}
