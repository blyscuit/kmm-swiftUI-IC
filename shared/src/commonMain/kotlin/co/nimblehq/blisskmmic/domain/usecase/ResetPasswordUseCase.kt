package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.repository.AccountRecoveryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ResetPasswordUseCase {

    operator fun invoke(email: String): Flow<String>
}

class ResetPasswordUseCaseImpl(private val repository: AccountRecoveryRepository) : ResetPasswordUseCase {

    override operator fun invoke(email: String): Flow<String> {
        return repository.resetPasswordWith(email)
            .map { meta ->
                meta.message
            }
    }
}
