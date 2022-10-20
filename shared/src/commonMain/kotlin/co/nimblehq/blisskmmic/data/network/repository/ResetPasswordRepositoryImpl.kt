package co.nimblehq.blisskmmic.data.network.repository

import co.nimblehq.blisskmmic.data.model.ResetPasswordMeta
import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.target.ResetPasswordTargetType
import co.nimblehq.blisskmmic.domain.repository.ResetPasswordRepository
import kotlinx.coroutines.flow.Flow

class ResetPasswordRepositoryImpl(private val networkClient: NetworkClient): ResetPasswordRepository {

    override fun reset(email: String): Flow<ResetPasswordMeta> {
        return networkClient.fetch<ResetPasswordMeta>(
            ResetPasswordTargetType(email).requestBuilder
        )
    }
}
