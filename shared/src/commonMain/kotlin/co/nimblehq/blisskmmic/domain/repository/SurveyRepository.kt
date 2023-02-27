package co.nimblehq.blisskmmic.domain.repository

import co.nimblehq.blisskmmic.domain.model.*
import kotlinx.coroutines.flow.Flow

interface SurveyRepository {

    fun getSurvey(page: Int): Flow<Pair<List<Survey>, PaginationMeta>>
    fun getSurveyDetail(id: String): Flow<SurveyDetail>
    fun submitSurvey(surveySubmission: SurveySubmission): Flow<Unit>
}
