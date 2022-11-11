package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.presentation.modules.login.LoginViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<LoginViewModel> { LoginViewModel(get()) }
}
