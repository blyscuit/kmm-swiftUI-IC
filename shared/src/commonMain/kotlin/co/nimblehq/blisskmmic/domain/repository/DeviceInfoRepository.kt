package co.nimblehq.blisskmmic.domain.repository

import co.nimblehq.blisskmmic.domain.model.DateComponents
import kotlinx.coroutines.flow.Flow

interface DeviceInfoRepository {

    fun getCurrentDate(): Flow<DateComponents>
}
