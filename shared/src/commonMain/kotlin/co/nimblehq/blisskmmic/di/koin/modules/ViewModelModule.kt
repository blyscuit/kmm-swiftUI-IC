package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.presentation.modules.login.LoginViewModel
import co.nimblehq.blisskmmic.presentation.modules.resetpassword.ResetPasswordViewModel
import co.nimblehq.blisskmmic.presentation.modules.splash.SplashViewModel
import co.nimblehq.blisskmmic.presentation.modules.surveyselection.SurveySelectionViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {

    factoryOf(::LoginViewModel)
    factoryOf(::ResetPasswordViewModel)
    factoryOf(::SplashViewModel)
    factoryOf(::SurveySelectionViewModel)
}
