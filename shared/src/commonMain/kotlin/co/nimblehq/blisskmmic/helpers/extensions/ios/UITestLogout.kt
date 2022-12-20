package co.nimblehq.blisskmmic.helpers.extensions.ios

import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSourceImpl
import com.russhwolf.settings.Settings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object UITestLogout: KoinComponent {

    fun logOut() {
        // TODO: Use logout use case to clear
        val settings : Settings by inject()
        settings.clear()
    }
}
