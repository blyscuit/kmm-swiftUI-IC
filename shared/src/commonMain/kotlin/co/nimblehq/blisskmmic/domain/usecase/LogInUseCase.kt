package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class LogInUseCase(private val repository: UserRepository) {

    operator fun invoke(email: String, password: String): Flow<Token> {
        return repository.logIn(email, password)
    }
}
