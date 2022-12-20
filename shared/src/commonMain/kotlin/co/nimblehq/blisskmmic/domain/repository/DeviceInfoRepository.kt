package co.nimblehq.blisskmmic.domain.repository

import co.nimblehq.blisskmmic.domain.model.DateComponent
import kotlinx.coroutines.flow.Flow

interface DeviceInfoRepository {

    fun getCurrentDate(): Flow<DateComponent>
}
