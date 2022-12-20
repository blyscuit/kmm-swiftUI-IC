package co.nimblehq.blisskmmic.domain.repository

import co.nimblehq.blisskmmic.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getProfile(): Flow<User>
}
