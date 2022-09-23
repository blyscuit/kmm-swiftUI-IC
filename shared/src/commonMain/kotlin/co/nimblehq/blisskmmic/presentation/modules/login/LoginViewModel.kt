package co.nimblehq.blisskmmic.presentation.modules.login

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCase
import kotlinx.coroutines.flow.Flow

class LoginViewModel(private val logInUseCase: LogInUseCase) {

    fun login(email: String, password: String) : Flow<Token> {
        return logInUseCase(email, password)
    }
}
