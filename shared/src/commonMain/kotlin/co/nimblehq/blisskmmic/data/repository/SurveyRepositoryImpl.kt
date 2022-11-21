package co.nimblehq.blisskmmic.data.repository

import co.nimblehq.blisskmmic.data.model.*
import co.nimblehq.blisskmmic.data.network.datasource.NetworkDataSource
import co.nimblehq.blisskmmic.data.network.target.SurveySelectionTargetType
import co.nimblehq.blisskmmic.domain.model.PaginationMeta
import co.nimblehq.blisskmmic.domain.model.Survey
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SurveyRepositoryImpl(private val networkDataSource: NetworkDataSource): SurveyRepository {

    override fun survey(page: Int): Flow<Pair<List<Survey>, PaginationMeta>> {
        return networkDataSource.survey(SurveySelectionTargetType(page))
            .map {
                Pair(
                    it.first.map { item -> item.toSurvey() },
                    it.second.toPaginationMeta()
                )
            }
    }
}
