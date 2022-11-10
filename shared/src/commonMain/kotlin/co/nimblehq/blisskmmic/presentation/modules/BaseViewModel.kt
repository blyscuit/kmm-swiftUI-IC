package co.nimblehq.blisskmmic.presentation.modules

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel() {
    val viewModelScope: CoroutineScope
    protected open fun onCleared()
}
