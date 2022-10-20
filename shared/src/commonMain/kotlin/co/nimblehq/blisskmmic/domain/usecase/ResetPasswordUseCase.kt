package co.nimblehq.blisskmmic.domain.usecase

import co.nimblehq.blisskmmic.domain.model.Token
import co.nimblehq.blisskmmic.domain.repository.ResetPasswordRepository
import co.nimblehq.blisskmmic.domain.repository.TokenRepository
import co.nimblehq.jsonapi.model.ApiJson
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.test.fail

interface ResetPasswordUseCase {

    operator fun invoke(email: String): Flow<String>
}

class ResetPasswordUseCaseImpl(private val repository: ResetPasswordRepository) : ResetPasswordUseCase {

    override operator fun invoke(email: String): Flow<String> {
        return repository.reset(email)
            .map { meta ->
                val item = meta.value["message"]
                when(item) {
                    is ApiJson.string -> item.value
                    // TODO: Use localized error
                    else -> error("An error occurred.")
                }
            }
    }
}
