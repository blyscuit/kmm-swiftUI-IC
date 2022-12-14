package co.nimblehq.blisskmmic.domain.platform.datetime

import platform.Foundation.NSDate

actual class DateTimeFormatterImpl: DateTimeFormatter {

    actual override fun getFormattedString(
        secondsSinceEpoch: Long,
        format: DateFormat
    ): String {
        val date = NSDate(secondsSinceEpoch.toDouble())
        return DateFormatter.get(format).stringFromDate(date)
    }
}
