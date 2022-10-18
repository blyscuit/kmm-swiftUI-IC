package co.nimblehq.blisskmmic.data.network.repository

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.domain.model.TokenApiModel
import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.model.toToken
import co.nimblehq.blisskmmic.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenRepositoryImpl(private val networkClient: NetworkClient): TokenRepository {

    override fun logIn(email: String, password: String): Flow<Token> {
        return networkClient.fetch<TokenApiModel>(
            LoginTargetType(email, password).requestBuilder
        ).map { it.toToken() }
    }
}
