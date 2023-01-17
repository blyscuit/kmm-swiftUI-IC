package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.domain.platform.VersionCode
import co.nimblehq.blisskmmic.domain.repository.AppInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class AppInfoRepositoryImpl(
    private val version: VersionCode
): AppInfoRepository {

    override fun getAppVersion(): Flow<String> {
        return flowOf(version.versionCode)
    }

    override fun getAppBuildNumber(): Flow<String> {
        return flowOf(version.buildNumber)
    }
}
