package co.nimblehq.blisskmmic.data.network.datasource

import co.nimblehq.blisskmmic.data.model.PaginationMetaApiModel
import co.nimblehq.blisskmmic.data.model.SurveyApiModel
import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.network.target.LoginTargetType
import co.nimblehq.blisskmmic.data.network.target.SurveySelectionTargetType
import co.nimblehq.blisskmmic.data.network.target.SurveyTargetType
import co.nimblehq.blisskmmic.domain.model.PaginationMeta
import co.nimblehq.blisskmmic.domain.model.Survey
import co.nimblehq.blisskmmic.domain.model.TokenApiModel
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {

    fun logIn(target: LoginTargetType): Flow<TokenApiModel>
    fun survey(target: SurveyTargetType): Flow<Pair<List<SurveyApiModel>, PaginationMetaApiModel>>
}

class NetworkDataSourceImpl(private val networkClient: NetworkClient): NetworkDataSource {

    override fun logIn(target: LoginTargetType): Flow<TokenApiModel> {
        return networkClient.fetch(target.requestBuilder)
    }

    override fun survey(target: SurveyTargetType): Flow<Pair<List<SurveyApiModel>, PaginationMetaApiModel>> {
        return networkClient.fetchWithMeta<List<SurveyApiModel>, PaginationMetaApiModel>(
            target.requestBuilder
        )
    }
}
