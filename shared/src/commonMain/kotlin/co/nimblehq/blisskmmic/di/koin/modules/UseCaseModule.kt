package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single<LogInUseCase> { LogInUseCaseImpl(get()) }
    single<ResetPasswordUseCase> { ResetPasswordUseCaseImpl(get()) }
    single<SurveyListUseCase> { SurveyListUseCaseImpl(get()) }
    single<CheckLoginUseCase> { CheckLoginUseCaseImpl(get()) }
    single<GetCurrentDateUseCase> { GetCurrentDateUseCaseImpl(get()) }
    single<GetProfileUseCase> { GetProfileUseCaseImpl(get()) }
    single<GetAppVersionUseCase> { GetAppVersionUseCaseImpl(get()) }
    single<LogOutUseCase> { LogOutUseCaseImpl(get()) }
    single<GetSurveyDetailUseCase> { GetSurveyDetailUseCaseImpl(get()) }
    single<SubmitSurveyUseCase> { SubmitSurveyUseCaseImpl(get()) }
}
