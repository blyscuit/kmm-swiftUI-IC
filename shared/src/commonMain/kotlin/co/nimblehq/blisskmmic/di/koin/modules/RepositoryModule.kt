package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.repository.ResetPasswordRepositoryImpl
import co.nimblehq.blisskmmic.domain.repository.ResetPasswordRepository
import co.nimblehq.blisskmmic.data.repository.AuthenticationRepositoryImpl
import co.nimblehq.blisskmmic.domain.repository.AuthenticationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }
    single<ResetPasswordRepository> { ResetPasswordRepositoryImpl(get()) }
