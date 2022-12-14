package co.nimblehq.blisskmmic.domain.platform.datetime

interface DateTimeFormatter {

    fun getFormattedString(secondsSinceEpoch: Long, format: DateFormat): String
}

expect class DateTimeFormatterImpl(): DateTimeFormatter {

    override fun getFormattedString(secondsSinceEpoch: Long, format: DateFormat): String
}
