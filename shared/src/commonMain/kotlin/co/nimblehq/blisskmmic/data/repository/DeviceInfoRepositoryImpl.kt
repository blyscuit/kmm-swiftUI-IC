package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.domain.model.DateComponent
import co.nimblehq.blisskmmic.domain.repository.DeviceInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.*

class DeviceInfoRepositoryImpl(
    private val clock: Clock
): DeviceInfoRepository {

    override fun getCurrentDate(): Flow<DateComponent> {
        val now = clock.now()
        val dateHeader = DateComponent(
            now.epochSeconds
        )
        return flowOf(dateHeader)
    }
}
