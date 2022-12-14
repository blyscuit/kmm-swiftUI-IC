package co.nimblehq.blisskmmic.domain.platform.datetime

import platform.Foundation.NSDate

private const val REFERENCED_INTERVAL: Double = 978_307_200.0

actual class DateTimeFormatterImpl: DateTimeFormatter {

    actual override fun getFormattedString(
        secondsSinceEpoch: Long,
        format: DateFormat
    ): String {
        val realInterval = secondsSinceEpoch.toDouble() - REFERENCED_INTERVAL
        val date = NSDate(realInterval)
        return DateFormatter.get(format).stringFromDate(date)
    }
}
