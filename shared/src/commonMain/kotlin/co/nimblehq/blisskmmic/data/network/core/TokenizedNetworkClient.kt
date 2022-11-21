package co.nimblehq.blisskmmic.data.network.core

import co.nimblehq.blisskmmic.BuildKonfig
import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSource
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.singleOrNull

class TokenizedNetworkClient: NetworkClient {

    private val localDataSource: LocalDataSource

    constructor(
        engine: HttpClientEngine? = null,
        localDataSource: LocalDataSource
    ) : super(engine) {
        this.localDataSource = localDataSource
    }

    override fun clientConfig(): HttpClientConfig<*>.() -> Unit {
        return {
            install(Logging)
            install(ContentNegotiation) {
                json(json)
            }
            install(Auth) {
                bearer(bearerConfig())
            }
        }
    }

    private fun bearerConfig(): BearerAuthConfig.() -> Unit {
        return {
            loadTokens {
                localDataSource.getToken().singleOrNull()?.run {
                    BearerTokens(this.accessToken, this.refreshToken)
                }
            }
            sendWithoutRequest { request ->
                val builder = HttpRequestBuilder()
                builder.url("${BuildKonfig.BASE_URL}")
                request.url.host != builder.url.host
            }
        }
    }
}
