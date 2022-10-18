package co.nimblehq.blisskmmic.domain.repository

import co.nimblehq.blisskmmic.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface TokenRepository {

    fun logIn(email: String,  password: String): Flow<Token>
}
