package co.nimblehq.blisskmmic.models

import co.nimblehq.blisskmmic.supports.helpers.flow.FlowAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow

actual abstract class ViewModel {

    actual val viewModelScope = MainScope()

    /**
     * Override this to do any cleanup immediately before the 
     * internal [CoroutineScope][kotlinx.coroutines.CoroutineScope]
     * is cancelled in [clear]
     */
    protected actual open fun onCleared() {}

    fun clear() {
        onCleared()
        viewModelScope.cancel()
    }

    fun <T : Any> Flow<T>.asCallbacks() =
        FlowAdapter(viewModelScope, this)
}
