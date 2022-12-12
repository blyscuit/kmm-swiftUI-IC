package co.nimblehq.blisskmmic.helpers.extensions.ios

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

// For iOS Unit Test
class AnyFlow<T>(source: Flow<T>): Flow<T> by source {

    constructor(result: T) : this(flow { emit(result) })
    constructor(errorMessage: String) : this(flow { error(errorMessage) })
}

object WrappedFlow {

    fun bool(bool: Boolean): Flow<Boolean> {
        return flowOf(bool)
    }
    fun bool(errorMessage: String): Flow<Boolean> {
        return flow { error(errorMessage) }
    }
}
