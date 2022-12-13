package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.DateComponents
import co.nimblehq.blisskmmic.domain.model.DateReadable
import co.nimblehq.blisskmmic.domain.repository.DeviceInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

interface GetCurrentDateUseCase {

    operator fun invoke(): Flow<DateReadable>
}

class GetCurrentDateUseCaseImpl(
    private val deviceInfoRepository: DeviceInfoRepository
) : GetCurrentDateUseCase {

    override operator fun invoke(): Flow<DateReadable> {
        return deviceInfoRepository
            .getCurrentDate()
            .map {
                // Ideally should be mapped with localize, but with date-time is sufficient
                val dayOfWeek = DayOfWeek(it.dayOfWeek)
                val month = Month(it.month)
                DateReadable(
                    it.day,
                    month.name,
                    dayOfWeek.name
                )
            }
    }
}
