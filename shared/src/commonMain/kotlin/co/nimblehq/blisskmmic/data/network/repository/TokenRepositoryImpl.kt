package co.nimblehq.blisskmmic.data.network.repository

import co.nimblehq.blisskmmic.data.network.service.ApiService
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.model.toToken
import co.nimblehq.blisskmmic.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenRepositoryImpl(private val service: ApiService): TokenRepository {

    override fun logIn(email: String, password: String): Flow<Token> {
        return service.logIn(
            LoginTargetType(email, password)
        ).map { it.toToken() }
    }
}
