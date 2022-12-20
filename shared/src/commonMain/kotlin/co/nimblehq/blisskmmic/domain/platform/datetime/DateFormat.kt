package co.nimblehq.blisskmmic.domain.platform.datetime

sealed class DateFormat(val value: String) {
    object DayOfWeekMonthDay: DateFormat("EEEE, MMMM d")
}
