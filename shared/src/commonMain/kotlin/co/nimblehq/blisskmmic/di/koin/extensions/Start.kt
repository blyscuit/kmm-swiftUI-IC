package co.nimblehq.blisskmmic.di.koin.extensions

import co.nimblehq.blisskmmic.di.koin.initKoin
import co.nimblehq.blisskmmic.presentation.modules.login.LoginViewModel
import co.nimblehq.blisskmmic.presentation.modules.resetpassword.ResetPasswordViewModel
import co.nimblehq.blisskmmic.presentation.modules.splash.SplashViewModel
import co.nimblehq.blisskmmic.presentation.modules.surveydetail.SurveyDetailViewModel
import co.nimblehq.blisskmmic.presentation.modules.surveyselection.SurveySelectionViewModel
import org.koin.core.Koin
import org.koin.core.KoinApplication

fun KoinApplication.Companion.start(): KoinApplication = initKoin()

val Koin.logInViewModel: LoginViewModel
    get() = get()
val Koin.resetPasswordViewModel: ResetPasswordViewModel
    get() = get()
val Koin.splashViewModel: SplashViewModel
    get() = get()
val Koin.surveySelectionViewModel: SurveySelectionViewModel
    get() = get()
val Koin.surveyDetailViewModel: SurveyDetailViewModel
    get() = get()
