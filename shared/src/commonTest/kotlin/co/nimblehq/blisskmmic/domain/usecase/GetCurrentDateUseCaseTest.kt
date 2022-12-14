package co.nimblehq.blisskmmic.domain.usecase

import app.cash.turbine.test
import co.nimblehq.blisskmmic.domain.model.DateComponents
import co.nimblehq.blisskmmic.domain.repository.DeviceInfoRepository
import co.nimblehq.blisskmmic.domain.repository.MockDeviceInfoRepository
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@UsesMocks(DeviceInfoRepository::class)
@ExperimentalCoroutinesApi
class GetCurrentDateUseCaseTest {

    private val mocker = Mocker()
    private val deviceInfoRepository = MockDeviceInfoRepository(mocker)
    private val getCurrentDateUseCase = GetCurrentDateUseCaseImpl(deviceInfoRepository)
    private val dateComponents = DateComponents(day = 2, month = 2, dayOfWeek = 3)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling getCurrent with a success response, it returns correct object`() = runTest {
        mocker.every {
            deviceInfoRepository.getCurrentDate()
        } returns flowOf(dateComponents)

        getCurrentDateUseCase()
            .test {
                val item = awaitItem()
                item.day shouldBe 2
                item.dayOfWeek shouldBe "WEDNESDAY"
                item.month shouldBe "FEBRUARY"
                awaitComplete()
            }
    }

    @Test
    fun `When calling getCurrent with a failure response, it returns correct error`() = runTest {
        mocker.every {
            deviceInfoRepository.getCurrentDate()
        } returns flow { error("Fail") }

        getCurrentDateUseCase()
            .test {
                awaitError().message shouldBe "Fail"
            }
    }
}
