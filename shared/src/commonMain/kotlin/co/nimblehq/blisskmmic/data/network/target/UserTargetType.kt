package co.nimblehq.blisskmmic.data.network.target

import co.nimblehq.blisskmmic.BuildKonfig
import co.nimblehq.blisskmmic.data.network.helpers.TargetType
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class UserTargetType: TargetType

class LoginTargetType(email: String, password: String): UserTargetType() {

    @Serializable
    data class LoginInput(
        @SerialName("grant_type")
        val grantType: String,
        val email: String,
        val password: String,
        @SerialName("client_id")
        val clientId: String,
        @SerialName("client_secret")
        val clientSecret: String
    )

    override val path = "oauth/token"
    override val method = HttpMethod.Post
    override val body = LoginInput(
        "password",
        email,
        password,
        BuildKonfig.CLIENT_ID,
        BuildKonfig.CLIENT_SECRET
    )
    override val headers: Map<String, String>? = null
}


class ResetPasswordTargetType(email: String): UserTargetType() {

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

    override var baseURL: String = BuildKonfig.BASE_URL
    override var path: String = "passwords"
    override var method: HttpMethod = HttpMethod.Post
    override var body: Any? = ResetPasswordInput(
        User(email),
        BuildKonfig.CLIENT_ID,
        BuildKonfig.CLIENT_SECRET
    )
    override var headers: Map<String, String>? = null
}
