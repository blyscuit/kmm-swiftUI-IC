package co.nimblehq.blisskmmic.di.koin

import co.nimblehq.blisskmmic.di.koin.modules.*
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin() : KoinApplication {
    val dataModules = listOf(networkModule, repositoryModule, databaseModule)
    val domainModules = listOf(useCaseModule)
    val viewModelModules = listOf(viewModelModule)
    return startKoin {
        modules(
            domainModules + dataModules + viewModelModules + platformModule
        )
    }
}

expect val platformModule: Module
