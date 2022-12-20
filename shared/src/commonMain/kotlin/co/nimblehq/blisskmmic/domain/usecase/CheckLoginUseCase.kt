package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

interface CheckLoginUseCase {

    operator fun invoke(): Flow<Boolean>
}

class CheckLoginUseCaseImpl(
    private val authenticationRepository: AuthenticationRepository,
): CheckLoginUseCase {

    override operator fun invoke(): Flow<Boolean> {
        return authenticationRepository.hasCachedToken()
    }
}
