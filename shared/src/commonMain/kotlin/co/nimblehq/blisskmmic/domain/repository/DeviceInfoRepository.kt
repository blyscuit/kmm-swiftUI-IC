package co.nimblehq.blisskmmic.domain.repository

import kotlinx.coroutines.flow.Flow

interface DeviceInfoRepository {

    fun getCurrentDate(): Flow<Long>
}
