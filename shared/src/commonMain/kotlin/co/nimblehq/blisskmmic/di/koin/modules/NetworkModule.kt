package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.service.ApiService
import co.nimblehq.blisskmmic.data.network.service.ApiServiceImpl
import org.koin.dsl.module

val networkModule = module {
    single { NetworkClient() }
    single<ApiService> { ApiServiceImpl(get()) }
}
