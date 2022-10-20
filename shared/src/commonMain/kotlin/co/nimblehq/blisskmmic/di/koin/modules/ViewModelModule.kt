package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.presentation.modules.login.LoginViewModel
import co.nimblehq.blisskmmic.presentation.modules.resetpassword.ResetPasswordViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<LoginViewModel> { LoginViewModel(get()) }
    single { ResetPasswordViewModel(get()) }
}
