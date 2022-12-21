package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

interface LogOutUseCase {

    operator fun invoke(): Flow<Unit>
}

class LogOutUseCaseImpl(
    private val authenticationRepository: AuthenticationRepository
): LogOutUseCase {

    override operator fun invoke(): Flow<Unit> {
        return authenticationRepository.logOut()
    }
}
