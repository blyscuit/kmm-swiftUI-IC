package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.Survey
import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SurveyListUseCase {

    operator fun invoke(page: Int): Flow<List<Survey>>
}

class SurveyListUseCaseImpl(
    private val repository: SurveyRepository
) : SurveyListUseCase {

    override operator fun invoke(page: Int): Flow<List<Survey>> {
        return repository
            .survey(page)
            .map { it.first }
    }
}
