package co.nimblehq.blisskmmic.domain.platform.datetime

import io.kotest.matchers.shouldBe
import kotlin.test.Test

private const val PAST_TIME_SINCE = 1_000_000L
private const val TIME_SINCE = 1_000_000_000L

class DateTimeFormatterTest {

    private val dateTimeFormatter = DateTimeFormatterImpl()

    @Test
    fun `when getFormattedString with DayOfWeekMonthDay- it returns correct string`() {
        dateTimeFormatter.getFormattedString(TIME_SINCE, DateFormat.DayOfWeekMonthDay) shouldBe
            "Sunday, September 9"
    }

    @Test
    fun `when getFormattedString with past date and DayOfWeekMonthDay- it returns correct string`() {
        dateTimeFormatter.getFormattedString(PAST_TIME_SINCE, DateFormat.DayOfWeekMonthDay) shouldBe
            "Monday, January 12"
    }
}
