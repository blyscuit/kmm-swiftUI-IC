package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.core.TokenizedNetworkClient
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSourceImpl
import co.nimblehq.blisskmmic.di.koin.constants.NETWORK_CLIENT_KOIN
import co.nimblehq.blisskmmic.di.koin.constants.TOKENIZED_NETWORK_CLIENT_KOIN
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { NetworkClient() }
    single<NetworkDataSource>(named(NETWORK_CLIENT_KOIN)) { NetworkDataSourceImpl(get()) }
    single<NetworkDataSource>(named(TOKENIZED_NETWORK_CLIENT_KOIN)) {
        NetworkDataSourceImpl(TokenizedNetworkClient(null, get(), get(named(NETWORK_CLIENT_KOIN))))
    }
}
