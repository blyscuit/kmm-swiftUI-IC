package co.nimblehq.blisskmmic.domain.repository

import co.nimblehq.blisskmmic.data.network.repository.Message
import co.nimblehq.jsonapi.model.ApiJson
import co.nimblehq.jsonapi.model.JsonApiResponseType
import kotlinx.coroutines.flow.Flow

interface ResetPasswordRepository {

    fun reset(email: String): Flow<ApiJson.nested>
}
