package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.DateComponent
import co.nimblehq.blisskmmic.domain.repository.DeviceInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

interface GetCurrentDateUseCase {

    operator fun invoke(): Flow<DateComponent>
}

class GetCurrentDateUseCaseImpl(
    private val deviceInfoRepository: DeviceInfoRepository
) : GetCurrentDateUseCase {

    override operator fun invoke(): Flow<DateComponent> {
        return deviceInfoRepository
            .getCurrentDate()
    }
}
