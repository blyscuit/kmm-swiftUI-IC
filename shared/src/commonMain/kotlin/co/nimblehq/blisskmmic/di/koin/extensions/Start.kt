package co.nimblehq.blisskmmic.di.koin.extensions

import co.nimblehq.blisskmmic.di.koin.initKoin
import co.nimblehq.blisskmmic.presentation.modules.login.LoginViewModel
import co.nimblehq.blisskmmic.presentation.modules.resetpassword.ResetPasswordViewModel
import org.koin.core.Koin
import org.koin.core.KoinApplication

fun KoinApplication.Companion.start(): KoinApplication = initKoin()

val Koin.logInViewModel: LoginViewModel
    get() = get()
val Koin.resetPasswordViewModel: ResetPasswordViewModel
    get() = get()
