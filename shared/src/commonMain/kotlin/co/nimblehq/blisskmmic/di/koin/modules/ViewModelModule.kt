package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.presentation.modules.login.LoginViewModel
import co.nimblehq.blisskmmic.presentation.modules.resetpassword.ResetPasswordViewModel
import co.nimblehq.blisskmmic.presentation.modules.splash.SplashViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {

    singleOf(::LoginViewModel)
    singleOf(::ResetPasswordViewModel)
    singleOf(::SplashViewModel)
}
