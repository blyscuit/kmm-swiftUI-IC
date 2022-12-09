package co.nimblehq.blisskmmic.data.network.target

import co.nimblehq.blisskmmic.BuildKonfig
import co.nimblehq.blisskmmic.data.network.helpers.TargetType
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class ResetPasswordTargetType(email: String):
    TargetType<ResetPasswordTargetType.ResetPasswordInput> {

    @Serializable
    data class User(
        val email: String
    )

    @Serializable
    data class ResetPasswordInput(
        val user: User,
        @SerialName("client_id")
        val clientId: String,
        @SerialName("client_secret")
        val clientSecret: String
    )

    override var path = "passwords"
    override var method = HttpMethod.Post
    override var body = ResetPasswordInput(
        User(email),
        BuildKonfig.CLIENT_ID,
        BuildKonfig.CLIENT_SECRET
    )
}
