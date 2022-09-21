package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import org.koin.dsl.module

val networkModule = module {
    single { NetworkClient() }
}
