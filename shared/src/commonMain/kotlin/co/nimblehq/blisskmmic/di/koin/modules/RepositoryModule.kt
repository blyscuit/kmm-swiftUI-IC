package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.repository.TokenRepositoryImpl
import co.nimblehq.blisskmmic.domain.repository.TokenRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TokenRepository> { TokenRepositoryImpl(get(), get()) }
}
