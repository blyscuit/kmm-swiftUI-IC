package co.nimblehq.blisskmmic.data.database.datasource

import co.nimblehq.blisskmmic.data.database.core.DataStore
import co.nimblehq.blisskmmic.data.database.model.TokenDatabaseModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun save(token: TokenDatabaseModel)
    fun getToken(): Flow<TokenDatabaseModel>
    fun removeToken()
}

class LocalDataSourceImpl(private val dataStore: DataStore): LocalDataSource {

    companion object {
        internal const val DB_USER_SESSION_KEY = "DbUserSessionKey"
    }
    override fun save(token: TokenDatabaseModel) {
        dataStore.save(TokenDatabaseModel.serializer(), DB_USER_SESSION_KEY, token)
    }

    override fun getToken(): Flow<TokenDatabaseModel> {
        return dataStore
            .get(TokenDatabaseModel.serializer(), DB_USER_SESSION_KEY)
    }

    override fun removeToken() {
        dataStore.remove(DB_USER_SESSION_KEY)
    }
}
