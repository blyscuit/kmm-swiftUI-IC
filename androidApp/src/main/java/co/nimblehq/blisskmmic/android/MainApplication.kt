package co.nimblehq.blisskmmic.android

import android.app.Application
import co.nimblehq.blisskmmic.di.koin.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MainApplication)
        }
    }
}
