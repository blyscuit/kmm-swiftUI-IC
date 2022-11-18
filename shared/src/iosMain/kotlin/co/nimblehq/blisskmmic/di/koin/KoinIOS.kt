package co.nimblehq.blisskmmic.di.koin

import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import org.koin.dsl.module

actual val platformModule = module {

    single<Settings> { KeychainSettings() }
}
