package co.nimblehq.blisskmmic.helpers.mock.ktor

import co.nimblehq.blisskmmic.data.network.helpers.API_VERSION

fun apiPath(fullPath: String): String {
    return fullPath.split(API_VERSION).last()
}
