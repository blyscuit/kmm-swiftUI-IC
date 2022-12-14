package co.nimblehq.blisskmmic.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenApiModel(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("expires_in")
    val expiresIn: Int,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("created_at")
    val createdAt: Int
) {
    fun toToken(): Token = Token(
        accessToken,
        tokenType,
        expiresIn,
        refreshToken,
        createdAt
    )
}
