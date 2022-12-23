package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.repository.DeviceInfoRepository
import kotlinx.coroutines.flow.Flow

interface GetCurrentDateUseCase {

    operator fun invoke(): Flow<Long>
}

class GetCurrentDateUseCaseImpl(
    private val deviceInfoRepository: DeviceInfoRepository
) : GetCurrentDateUseCase {

    override operator fun invoke(): Flow<Long> {
        return deviceInfoRepository.getCurrentDate()
    }
}
