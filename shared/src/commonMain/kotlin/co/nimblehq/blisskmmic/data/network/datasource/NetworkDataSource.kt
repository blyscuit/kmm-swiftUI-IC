package co.nimblehq.blisskmmic.data.network.datasource

import co.nimblehq.blisskmmic.data.model.*
import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import co.nimblehq.blisskmmic.data.model.PaginationMetaApiModel
import co.nimblehq.blisskmmic.data.model.SurveyApiModel
import co.nimblehq.blisskmmic.data.model.UserApiModel
import co.nimblehq.blisskmmic.data.network.helpers.requestBuilder
import co.nimblehq.blisskmmic.data.network.target.*
import co.nimblehq.blisskmmic.domain.model.TokenApiModel
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {

    fun logIn(target: LoginTargetType): Flow<TokenApiModel>
    fun resetPassword(target: ResetPasswordTargetType): Flow<ResetPasswordMeta>
    fun survey(target: SurveySelectionTargetType): Flow<Pair<List<SurveyApiModel>, PaginationMetaApiModel>>
    fun surveyDetail(target: SurveyDetailTargetType): Flow<SurveyDetailApiModel>
    fun profile(target: UserProfileTargetType): Flow<UserApiModel>
    fun refreshToken(target: RefreshTokenType): Flow<TokenApiModel>
    fun submitSurvey(target: SubmitSurveyTargetType): Flow<Unit>
}

class NetworkDataSourceImpl(private val networkClient: NetworkClient): NetworkDataSource {

    override fun logIn(target: LoginTargetType): Flow<TokenApiModel> {
        return networkClient.fetch(target.requestBuilder())
    }

    override fun resetPassword(target: ResetPasswordTargetType): Flow<ResetPasswordMeta> {
        return networkClient.fetch(target.requestBuilder())
    }

    override fun survey(target: SurveySelectionTargetType):
        Flow<Pair<List<SurveyApiModel>, PaginationMetaApiModel>> {
        return networkClient.fetchWithMeta(target.requestBuilder())
    }

    override fun profile(target: UserProfileTargetType): Flow<UserApiModel> {
        return networkClient.fetch(target.requestBuilder())
    }

    override fun refreshToken(target: RefreshTokenType): Flow<TokenApiModel> {
        return networkClient.fetch(target.requestBuilder())
    }

    override fun surveyDetail(target: SurveyDetailTargetType): Flow<SurveyDetailApiModel> {
        return networkClient.fetch(target.requestBuilder())
    }

    override fun submitSurvey(target: SubmitSurveyTargetType): Flow<Unit> {
        return networkClient.fetchEmpty(target.requestBuilder())
    }
}
