package co.nimblehq.blisskmmic.data.network.service

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.domain.model.TokenApiModel
import kotlinx.coroutines.flow.Flow

interface ApiService {

    fun logIn(target: LoginTargetType): Flow<TokenApiModel>
}

class ApiServiceImpl(private val networkClient: NetworkClient): ApiService {

    override fun logIn(target: LoginTargetType): Flow<TokenApiModel> {
        return networkClient.fetch(target.requestBuilder)
    }
}
