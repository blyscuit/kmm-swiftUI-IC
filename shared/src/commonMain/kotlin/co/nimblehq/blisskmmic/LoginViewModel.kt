package co.nimblehq.blisskmmic

import co.nimblehq.blisskmmic.data.network.core.NetworkClient
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class LoginViewModel(
    private val networkClient: NetworkClient
) {
    private val mutableBreedState: MutableStateFlow<BreedViewState> =
        MutableStateFlow(BreedViewState(isLoading = true))

    val breedState: StateFlow<BreedViewState> = mutableBreedState

    var arr = listOf("Hello")

    val viewModelScope: CoroutineScope = MainScope()

    init {
        observeBreeds()
        viewModelScope.launch {
            add("1")
            delay(500)
            add("2")
            delay(500)
            add("3")
            delay(500)
        }
    }

    fun add(text: String) {
        arr = arr.plus(text)
        mutableBreedState.update {
            BreedViewState(breeds = arr)
        }
    }

    fun observeBreeds() {
        viewModelScope.launch {
            flow {
                emit(arr)
            }.catch {
                handleBreedError(it)
            }.collect { value ->
                mutableBreedState.update {
                    BreedViewState(breeds = value)
                }
            }
        }
    }

    private fun handleBreedError(throwable: Throwable) {
        mutableBreedState.update {
            if (it.breeds.isNullOrEmpty()) {
                BreedViewState(error = "Unable to refresh breed list")
            } else {
                // Just let it fail silently if we have a cache
                it.copy(isLoading = false)
            }
        }
    }
}

data class BreedViewState(
    val breeds: List<String>? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false
)
