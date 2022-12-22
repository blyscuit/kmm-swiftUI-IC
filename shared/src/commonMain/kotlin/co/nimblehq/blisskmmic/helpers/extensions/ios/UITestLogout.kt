package co.nimblehq.blisskmmic.helpers.extensions.ios

import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object UITestLogout: KoinComponent {

    fun logOut() {
        val localDataSource: LocalDataSource by inject()
        localDataSource.removeToken()
    }
}
