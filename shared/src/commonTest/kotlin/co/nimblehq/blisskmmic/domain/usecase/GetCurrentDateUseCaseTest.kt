package co.nimblehq.blisskmmic.domain.usecase

import app.cash.turbine.test
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

private const val TIME = 100L

@UsesMocks(DeviceInfoRepository::class)
@ExperimentalCoroutinesApi
class GetCurrentDateUseCaseTest {

    private val mocker = Mocker()
    private val deviceInfoRepository = MockDeviceInfoRepository(mocker)
    private val getCurrentDateUseCase = GetCurrentDateUseCaseImpl(deviceInfoRepository)

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling getCurrent with a success response - it returns correct object`() = runTest {
        mocker.every {
            deviceInfoRepository.getCurrentDate()
        } returns flowOf(TIME)

        getCurrentDateUseCase()
            .test {
                awaitItem() shouldBe TIME
                awaitComplete()
            }
    }

    @Test
    fun `When calling getCurrent with a failure response - it returns correct error`() = runTest {
        mocker.every {
            deviceInfoRepository.getCurrentDate()
        } returns flow { error("Fail") }

        getCurrentDateUseCase()
            .test {
                awaitError().message shouldBe "Fail"
            }
    }
}
