package co.nimblehq.blisskmmic.di.koin.modules

import co.nimblehq.blisskmmic.data.database.core.DataStore
import co.nimblehq.blisskmmic.data.database.core.DataStoreImpl
import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSource
import co.nimblehq.blisskmmic.data.database.datasource.LocalDataSourceImpl
import org.koin.dsl.module

val databaseModule = module {
    single<DataStore> { DataStoreImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get()) }
}
