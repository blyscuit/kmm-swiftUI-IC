package co.nimblehq.blisskmmic.data.repository

import app.cash.turbine.test
import co.nimblehq.blisskmmic.domain.platform.MockVersionCode
import co.nimblehq.blisskmmic.domain.platform.VersionCode
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import kotlin.test.BeforeTest
import kotlin.test.Test

@UsesMocks(VersionCode::class)
@ExperimentalCoroutinesApi
class AppInfoRepositoryTest {

    private val mocker = Mocker()
    private val versionCode = MockVersionCode(mocker)
    private val appInfoRepository = AppInfoRepositoryImpl(versionCode)
    private val appVersion = "Version"
    private val buildNumber = "Number"

    @BeforeTest
    fun setUp() {
        mocker.reset()
    }

    @Test
    fun `When calling getAppVersion with success response- it returns correct object`() = runTest {
        mocker.every {
            versionCode.versionCode
        } returns appVersion
        appInfoRepository
            .getAppVersion()
            .test {
                awaitItem() shouldBe appVersion
                awaitComplete()
            }
    }

    @Test
    fun `When calling getAppBuildNumber with success response- it returns correct object`() = runTest {
        mocker.every {
            versionCode.buildNumber
        } returns buildNumber
        appInfoRepository
            .getAppBuildNumber()
            .test {
                awaitItem() shouldBe buildNumber
                awaitComplete()
            }
    }
}
