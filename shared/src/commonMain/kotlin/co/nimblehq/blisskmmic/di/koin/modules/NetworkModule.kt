package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSourceImpl
import org.koin.dsl.module

val networkModule = module {
    single { NetworkClient() }
    single<NetworkDataSource> { NetworkDataSourceImpl(get()) }
}
