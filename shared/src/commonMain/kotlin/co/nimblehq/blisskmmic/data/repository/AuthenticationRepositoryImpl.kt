package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSource
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class AuthenticationRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
): AuthenticationRepository {

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

    override fun hasCachedToken(): Flow<Boolean> {
        return localDataSource.getToken()
            .map { true }
            .catch { emit(false) }
    }

    fun save(token: Token) {
        localDataSource.save(TokenDatabaseModel(token))
    }
}
