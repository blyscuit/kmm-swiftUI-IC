package co.nimblehq.blisskmmic.di.koin

import co.nimblehq.blisskmmic.di.koin.modules.networkModule
import co.nimblehq.blisskmmic.di.koin.modules.repositoryModule
import co.nimblehq.blisskmmic.di.koin.modules.useCaseModule
import co.nimblehq.blisskmmic.di.koin.modules.viewModelModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin() : KoinApplication {
    val dataModules = listOf(networkModule, repositoryModule)
    val domainModules = listOf(useCaseModule)
    val viewModelModules = listOf(viewModelModule)
    return startKoin {
        modules(
            domainModules + dataModules + viewModelModules
        )
    }
}
