package co.nimblehq.blisskmmic.data.network.target

import co.nimblehq.blisskmmic.BuildKonfig
import co.nimblehq.blisskmmic.data.network.helpers.TargetType
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class LogoutTargetType(token: String):
    TargetType<LogoutTargetType.LogoutInput> {

    @Serializable
    data class LogoutInput(
        val token: String,
        @SerialName("client_id")
        val clientId: String,
        @SerialName("client_secret")
        val clientSecret: String
    )

    override val path = "oauth/revoke"
    override val method = HttpMethod.Post
    override val body = LogoutInput(
        token,
        BuildKonfig.CLIENT_ID,
        BuildKonfig.CLIENT_SECRET
    )
}
