package co.nimblehq.blisskmmic.domain.platform.datetime

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.test.AfterTest
import kotlin.test.Test

class DateFormatterTest {

    @AfterTest
    fun tearDown() {
        DateFormatter.dateFormatters.clear()
    }

    @Test
    fun `when init - dateFormatters should be empty`() {
        DateFormatter.dateFormatters.count() shouldBe 0
    }

    @Test
    fun `when get - dateFormatters should have value`() {
        DateFormatter.get(DateFormat.DayOfWeekMonthDay)
        DateFormatter.dateFormatters.count() shouldNotBe 0
    }

    @Test
    fun `when get - it return a DateTimeFormatter`() {
        DateFormatter.get(DateFormat.DayOfWeekMonthDay) shouldNotBe null
    }

    @Test
    fun `when get same date formatter twice - it returns the same object`() {
        val first = DateFormatter.get(DateFormat.DayOfWeekMonthDay)
        val second = DateFormatter.get(DateFormat.DayOfWeekMonthDay)
        first shouldBe second
    }

    @Test
    fun `when get same date formatter twice - dateFormatters should have correct value`() {
        DateFormatter.get(DateFormat.DayOfWeekMonthDay)
        DateFormatter.get(DateFormat.DayOfWeekMonthDay)
        DateFormatter.dateFormatters.count() shouldBe 1
    }
}
