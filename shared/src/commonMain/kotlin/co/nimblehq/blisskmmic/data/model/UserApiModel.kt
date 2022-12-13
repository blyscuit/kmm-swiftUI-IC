package co.nimblehq.blisskmmic.data.model

import co.nimblehq.blisskmmic.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApiModel(
    val id: String,
    val type: String,
    val email: String,
    val name: String,
    @SerialName("avatar_url") val avatarUrl: String
) {

    fun toUser() = User(
        name,
        avatarUrl
    )
}
