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

private const val TIME: Long = 100_000

@UsesMocks(Clock::class)
@ExperimentalCoroutinesApi
class DeviceInfoRepositoryTest {
    private val mocker = Mocker()
    private val clock = MockClock(mocker)
    private val instant = Instant.fromEpochSeconds(TIME)
    private val deviceInfoRepository = DeviceInfoRepositoryImpl(clock)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling profile with success response - it returns correct object`() = runTest {
        mocker.every {
            clock.now()
        } returns instant
        deviceInfoRepository
            .getCurrentDate()
            .test {
                awaitItem() shouldBe TIME
                awaitComplete()
            }
    }
}
