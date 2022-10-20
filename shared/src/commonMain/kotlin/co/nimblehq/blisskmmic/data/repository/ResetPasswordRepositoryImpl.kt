package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.model.ResetPasswordMeta
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.data.network.target.ResetPasswordTargetType
import co.nimblehq.blisskmmic.domain.repository.ResetPasswordRepository
import kotlinx.coroutines.flow.Flow

class ResetPasswordRepositoryImpl(private val networkDataSource: NetworkDataSource): ResetPasswordRepository {

    override fun reset(email: String): Flow<ResetPasswordMeta> {
        return networkDataSource.resetPassword(ResetPasswordTargetType(email))
    }
}
