package co.nimblehq.blisskmmic.domain.platform.datetime

actual class DateTimeFormatterImpl: DateTimeFormatter {

    actual override fun getFormattedString(
        secondsSinceEpoch: Long,
        format: DateFormat
    ): String {
        // TODO: implement date for Android
        return ""
    }
}
