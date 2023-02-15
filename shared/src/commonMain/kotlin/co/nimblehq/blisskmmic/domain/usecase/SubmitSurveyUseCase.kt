package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.SurveySubmission
import co.nimblehq.blisskmmic.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow

interface SubmitSurveyUseCase {

    operator fun invoke(submission: SurveySubmission): Flow<Unit>
}

class SubmitSurveyUseCaseImpl(private val repository: SurveyRepository) : SubmitSurveyUseCase {

    override operator fun invoke(submission: SurveySubmission): Flow<Unit> {
        return repository.submitSurvey(submission)
    }
}
