package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.network.repository.UserRepositoryImpl
import co.nimblehq.blisskmmic.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}
