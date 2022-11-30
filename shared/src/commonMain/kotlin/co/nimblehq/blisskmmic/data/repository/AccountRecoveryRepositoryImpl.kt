package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.model.ResetPasswordMeta
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.data.network.target.ResetPasswordTargetType
import co.nimblehq.blisskmmic.domain.repository.AccountRecoveryRepository
import kotlinx.coroutines.flow.Flow

class AccountRecoveryRepositoryImpl(private val networkDataSource: NetworkDataSource): AccountRecoveryRepository {

    override fun resetPasswordWith(email: String): Flow<ResetPasswordMeta> {
        return networkDataSource.resetPassword(ResetPasswordTargetType(email))
    }
}
