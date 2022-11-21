package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.repository.SurveyRepositoryImpl
import co.nimblehq.blisskmmic.data.repository.TokenRepositoryImpl
import co.nimblehq.blisskmmic.di.koin.constants.NETWORK_CLIENT_KOIN
import co.nimblehq.blisskmmic.di.koin.constants.TOKENIZED_NETWORK_CLIENT_KOIN
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import co.nimblehq.blisskmmic.domain.repository.TokenRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<TokenRepository> { TokenRepositoryImpl(get(named(NETWORK_CLIENT_KOIN)), get()) }
    factory<SurveyRepository> { SurveyRepositoryImpl(get(named(TOKENIZED_NETWORK_CLIENT_KOIN))) }
}
