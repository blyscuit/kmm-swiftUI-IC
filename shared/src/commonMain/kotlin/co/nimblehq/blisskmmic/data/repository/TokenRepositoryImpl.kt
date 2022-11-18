package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSource
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import co.nimblehq.blisskmmic.data.database.model.toToken
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.model.toToken
import co.nimblehq.blisskmmic.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
): TokenRepository {

    override fun logIn(email: String, password: String): Flow<Token> {
        return networkDataSource.logIn(
            LoginTargetType(email, password)
        ).map {
            val token = it.toToken()
            save(token)
            token
        }
    }

    override fun getCachedToken(): Flow<Token> {
        return localDataSource.getToken()
            .map { it.toToken() }
    }

    fun save(token: Token) {
        localDataSource.save(
            TokenDatabaseModel(
                token.accessToken,
                token.tokenType,
                token.expiresIn,
                token.refreshToken,
                token.createdAt
            )
        )
    }
}
