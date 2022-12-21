package co.nimblehq.blisskmmic.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppInfoRepository {
    fun getAppVersion(): Flow<String>
    fun getAppBuildNumber(): Flow<String>
}
