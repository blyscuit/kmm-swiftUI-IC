package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

interface LogInUseCase {

    operator fun invoke(email: String, password: String): Flow<Token>
}

class LogInUseCaseImpl(
    private val authenticationRepository: AuthenticationRepository,
): LogInUseCase {

    override operator fun invoke(
        email: String, password: String): Flow<Token> {
        return authenticationRepository.logIn(email, password)
    }
}
