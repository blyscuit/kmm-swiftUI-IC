package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.SurveyDetail
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow

interface GetSurveyDetailUseCase {

    operator fun invoke(id: String): Flow<SurveyDetail>
}

class GetSurveyDetailUseCaseImpl(
    private val repository: SurveyRepository
) : GetSurveyDetailUseCase {

    override operator fun invoke(id: String): Flow<SurveyDetail> {
        return repository.getSurveyDetail(id)
    }
}
