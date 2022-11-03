package co.nimblehq.blisskmmic.presentation.modules

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class ViewModel {

    actual val viewModelScope = MainScope()

    protected actual open fun onCleared() {}

    /**
     * Cancels the internal [CoroutineScope][kotlinx.coroutines.CoroutineScope].
     * After this is called, the ViewModel should
     * no longer be used.
     */
    fun clear() {
        onCleared()
        viewModelScope.cancel()
    }
}
