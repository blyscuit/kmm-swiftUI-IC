package co.nimblehq.blisskmmic.data.network.core

import co.nimblehq.blisskmmic.BuildKonfig
import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSource
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.data.network.target.RefreshTokenType
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.singleOrNull

class TokenizedNetworkClient: NetworkClient {

    private val localDataSource: LocalDataSource
    private val networkDataSource: NetworkDataSource

    constructor(
        engine: HttpClientEngine? = null,
        localDataSource: LocalDataSource,
        networkDataSource: NetworkDataSource
    ) : super(engine) {
        this.localDataSource = localDataSource
        this.networkDataSource = networkDataSource
    }

    override fun clientConfig(): HttpClientConfig<*>.() -> Unit {
        return {
            install(Logging) {
                loggingConfig()
            }
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
                    BearerTokens(accessToken, refreshToken)
                }
            }
            sendWithoutRequest { request ->
                request.url.host == Url(BuildKonfig.BASE_URL).host
            }
            refreshTokens {
                networkDataSource
                    .refreshToken(RefreshTokenType(oldTokens?.refreshToken ?: ""))
                    .last()
                    .run {
                        localDataSource.save(TokenDatabaseModel(toToken()))
                        BearerTokens(accessToken, refreshToken)
                    }
            }
        }
    }
}
