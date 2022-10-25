package co.nimblehq.blisskmmic.domain.repository

import co.nimblehq.blisskmmic.domain.model.PaginationMeta
import co.nimblehq.blisskmmic.domain.model.Survey
import co.nimblehq.blisskmmic.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface SurveyRepository {

    fun survey(page: Int): Flow<Pair<List<Survey>, PaginationMeta>>
    fun surveyDetail(id: Int): Flow<SurveyDetailApiModel>
}
