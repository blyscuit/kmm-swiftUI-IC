package co.nimblehq.blisskmmic.domain.usecase

import app.cash.turbine.test
import co.nimblehq.blisskmmic.domain.repository.AppInfoRepository
import co.nimblehq.blisskmmic.domain.repository.MockAppInfoRepository
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@UsesMocks(AppInfoRepository::class)
@ExperimentalCoroutinesApi
class GetAppVersionUseCaseTest {

    private val mocker = Mocker()
    private val appInfoRepository = MockAppInfoRepository(mocker)
    private val getAppVersionUseCase = GetAppVersionUseCaseImpl(appInfoRepository)
    private val appVersion = "Version"
    private val buildNumber = "Number"

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When invoke with a success response - it returns correct object`() = runTest {
        mocker.every {
            appInfoRepository.getAppVersion()
        } returns flowOf(appVersion)
        mocker.every {
            appInfoRepository.getAppBuildNumber()
        } returns flowOf(buildNumber)

        getAppVersionUseCase()
            .test {
                val result = awaitItem()
                result.appVersion shouldBe appVersion
                result.buildNumber shouldBe buildNumber
                awaitComplete()
            }
    }

    @Test
    fun `When invoke with repository getAppVersion returns with a failure response - it returns correct error`() = runTest {
        mocker.every {
            appInfoRepository.getAppVersion()
        } returns flow { error("Fail") }
        mocker.every {
            appInfoRepository.getAppBuildNumber()
        } returns flowOf(buildNumber)

        getAppVersionUseCase()
            .test {
                awaitError().message shouldBe "Fail"
            }
    }

    @Test
    fun `When invoke with repository getAppBuildNumber returns with a failure response - it returns correct error`() = runTest {
        mocker.every {
            appInfoRepository.getAppVersion()
        } returns flowOf(appVersion)
        mocker.every {
            appInfoRepository.getAppBuildNumber()
        } returns flow { error("Fail") }

        getAppVersionUseCase()
            .test {
                awaitError().message shouldBe "Fail"
            }
    }
}
