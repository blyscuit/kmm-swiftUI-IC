package co.nimblehq.blisskmmic.data.repository

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.*
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@UsesMocks(Clock::class)
@ExperimentalCoroutinesApi
class DeviceInfoRepositoryTest {

    private val mocker = Mocker()
    private val clock = MockClock(mocker)
    private val instant = Instant.DISTANT_PAST
    private val deviceInfoRepository = DeviceInfoRepositoryImpl(clock)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling profile with success response, it returns correct object`() = runTest {
        mocker.every {
            clock.now()
        } returns instant
        deviceInfoRepository
            .getCurrentDate()
            .test {
                val item = awaitItem()
                val local = instant.toLocalDateTime(TimeZone.currentSystemDefault())
                item.day shouldBe local.dayOfMonth
                item.month shouldBe local.monthNumber
                item.dayOfWeek shouldBe local.dayOfWeek.isoDayNumber
                awaitComplete()
            }
    }
}
