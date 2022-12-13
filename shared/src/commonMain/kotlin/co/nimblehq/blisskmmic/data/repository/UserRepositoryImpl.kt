package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.data.network.target.UserProfileTargetType
import co.nimblehq.blisskmmic.domain.model.User
import co.nimblehq.blisskmmic.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val networkDataSource: NetworkDataSource
): UserRepository {

    override fun getProfile(): Flow<User> {
        return networkDataSource
            .getProfile(UserProfileTargetType())
            .map { it.toUser() }
    }
}
