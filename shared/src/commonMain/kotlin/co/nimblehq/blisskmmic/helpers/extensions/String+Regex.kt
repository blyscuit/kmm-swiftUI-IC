@file:Suppress("MatchingDeclarationName")
package co.nimblehq.blisskmmic.helpers.extensions

fun String.isValidEmail(): Boolean {

    val expression = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b"
    val regex = Regex(expression)
    return this.isNotEmpty() && this.matches(regex)
}
