package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

interface LogInUseCase {

    operator fun invoke(email: String, password: String): Flow<Token>
}

class LogInUseCaseImpl(private val repository: TokenRepository) : LogInUseCase {

    override operator fun invoke(email: String, password: String): Flow<Token> {
        return repository.logIn(email, password)
    }
}
