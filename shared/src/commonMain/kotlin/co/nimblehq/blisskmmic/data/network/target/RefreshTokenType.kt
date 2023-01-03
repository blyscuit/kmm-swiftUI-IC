package co.nimblehq.blisskmmic.data.network.target

import co.nimblehq.blisskmmic.BuildKonfig
import co.nimblehq.blisskmmic.data.network.helpers.TargetType
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class RefreshTokenType(refreshToken: String):
    TargetType<RefreshTokenType.RefreshTokenInput> {

    @Serializable
    data class RefreshTokenInput(
        @SerialName("grant_type") val grantType: String,
        @SerialName("refresh_token") val refreshToken: String,
        @SerialName("client_id") val clientId: String,
        @SerialName("client_secret") val clientSecret: String
    )

    override val path = "oauth/token"
    override val method = HttpMethod.Post
    override val body = RefreshTokenInput(
        "refresh_token",
        refreshToken,
        BuildKonfig.CLIENT_ID,
        BuildKonfig.CLIENT_SECRET
    )
}
