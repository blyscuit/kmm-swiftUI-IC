package co.nimblehq.blisskmmic.data.network.helpers

import co.nimblehq.blisskmmic.BuildKonfig

fun urlPath(path: String): String {
    return "${BuildKonfig.BASE_URL}${path}"
}
