package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.repository.*
import co.nimblehq.blisskmmic.di.koin.constants.NETWORK_CLIENT_KOIN
import co.nimblehq.blisskmmic.di.koin.constants.TOKENIZED_NETWORK_CLIENT_KOIN
import co.nimblehq.blisskmmic.domain.repository.*
import kotlinx.datetime.Clock
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(named(NETWORK_CLIENT_KOIN)), get()) }
    single<AccountRecoveryRepository> { AccountRecoveryRepositoryImpl(get(named(NETWORK_CLIENT_KOIN))) }
    single<SurveyRepository> { SurveyRepositoryImpl(get(named(TOKENIZED_NETWORK_CLIENT_KOIN))) }
    single<DeviceInfoRepository> { DeviceInfoRepositoryImpl(Clock.System) }
    single<UserRepository> { UserRepositoryImpl(get(named(TOKENIZED_NETWORK_CLIENT_KOIN))) }
}
