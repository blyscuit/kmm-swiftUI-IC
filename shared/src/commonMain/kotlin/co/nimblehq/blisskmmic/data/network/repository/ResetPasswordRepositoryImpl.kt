package co.nimblehq.blisskmmic.data.network.repository

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.target.ResetPasswordTargetType
import co.nimblehq.blisskmmic.domain.repository.ResetPasswordRepository
import co.nimblehq.jsonapi.model.ApiJson
import co.nimblehq.jsonapi.model.JsonApiResponseType
import kotlinx.coroutines.flow.Flow

class ResetPasswordRepositoryImpl(private val networkClient: NetworkClient): ResetPasswordRepository {

    override fun reset(email: String): Flow<ApiJson.nested> {
        return networkClient.fetch<ApiJson.nested>(
            ResetPasswordTargetType(email).requestBuilder
        )
    }
}
