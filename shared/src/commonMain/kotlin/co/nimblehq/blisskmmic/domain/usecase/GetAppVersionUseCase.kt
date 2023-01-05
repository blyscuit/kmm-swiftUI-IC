package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.AppVersion
import co.nimblehq.blisskmmic.domain.repository.AppInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip

interface GetAppVersionUseCase {

    operator fun invoke(): Flow<AppVersion>
}

class GetAppVersionUseCaseImpl(
    private val appInfoRepository: AppInfoRepository
) : GetAppVersionUseCase {

    override fun invoke(): Flow<AppVersion> {
        return appInfoRepository
            .getAppVersion()
            .zip(appInfoRepository.getAppBuildNumber()) { version, build ->
                AppVersion(version, build)
            }
    }
}
