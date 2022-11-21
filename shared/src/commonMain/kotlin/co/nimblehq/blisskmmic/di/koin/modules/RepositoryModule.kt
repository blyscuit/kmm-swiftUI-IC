package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.repository.AccountRecoveryRepositoryImpl
import co.nimblehq.blisskmmic.domain.repository.AccountRecoveryRepository
import co.nimblehq.blisskmmic.data.repository.AuthenticationRepositoryImpl
import co.nimblehq.blisskmmic.data.repository.SurveyRepositoryImpl
import co.nimblehq.blisskmmic.domain.repository.AuthenticationRepository
import co.nimblehq.blisskmmic.di.koin.constants.NETWORK_CLIENT_KOIN
import co.nimblehq.blisskmmic.di.koin.constants.TOKENIZED_NETWORK_CLIENT_KOIN
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(named(NETWORK_CLIENT_KOIN)), get()) }
    single<AccountRecoveryRepository> { AccountRecoveryRepositoryImpl(get(named(NETWORK_CLIENT_KOIN))) }
    factory<SurveyRepository> { SurveyRepositoryImpl(get(named(TOKENIZED_NETWORK_CLIENT_KOIN))) }
}
