package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.domain.usecase.LogInUseCase
import co.nimblehq.blisskmmic.domain.usecase.LogInUseCaseImpl
import co.nimblehq.blisskmmic.domain.usecase.SurveyListUseCase
import co.nimblehq.blisskmmic.domain.usecase.SurveyListUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<LogInUseCase> { LogInUseCaseImpl(get()) }
    factory<SurveyListUseCase> { SurveyListUseCaseImpl(get()) }
}
