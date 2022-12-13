package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.domain.model.DateComponents
import co.nimblehq.blisskmmic.domain.repository.DeviceInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.*

class DeviceInfoRepositoryImpl(
    private val clock: Clock
): DeviceInfoRepository {

    override fun getCurrentDate(): Flow<DateComponents> {
        val datetimeInSystemZone = clock.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val dateHeader = DateComponents(
            datetimeInSystemZone.dayOfMonth,
            datetimeInSystemZone.monthNumber,
            datetimeInSystemZone.dayOfWeek.isoDayNumber
        )
        return flow {
            emit(dateHeader)
        }
    }
}
