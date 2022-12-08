package co.nimblehq.blisskmmic.domain.repository

import co.nimblehq.blisskmmic.data.model.ResetPasswordMeta
import kotlinx.coroutines.flow.Flow

interface AccountRecoveryRepository {

    fun resetPasswordWith(email: String): Flow<ResetPasswordMeta>
}
