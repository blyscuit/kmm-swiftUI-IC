package co.nimblehq.blisskmmic.data.network.repository

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.domain.model.ApiToken
import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.model.toToken
import co.nimblehq.blisskmmic.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(private val networkClient: NetworkClient): UserRepository {

    override fun logIn(email: String, password: String): Flow<Token> {

        return networkClient.fetch<ApiToken>(
            LoginTargetType(email, password).requestBuilder
        ).map { it.toToken() }
    }
}
