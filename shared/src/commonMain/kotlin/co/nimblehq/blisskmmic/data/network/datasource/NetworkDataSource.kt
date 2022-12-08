package co.nimblehq.blisskmmic.data.network.datasource

import co.nimblehq.blisskmmic.data.model.ResetPasswordMeta
import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.data.network.target.ResetPasswordTargetType
import co.nimblehq.blisskmmic.domain.model.TokenApiModel
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {

    fun logIn(target: LoginTargetType): Flow<TokenApiModel>
    fun resetPassword(target: ResetPasswordTargetType): Flow<ResetPasswordMeta>
}

class NetworkDataSourceImpl(private val networkClient: NetworkClient): NetworkDataSource {

    override fun logIn(target: LoginTargetType): Flow<TokenApiModel> {
        return networkClient.fetch(target.requestBuilder)
    }

    override fun resetPassword(target: ResetPasswordTargetType): Flow<ResetPasswordMeta> {
        return networkClient.fetch(target.requestBuilder)
    }
}
