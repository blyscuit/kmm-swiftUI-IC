package co.nimblehq.blisskmmic.helpers.extensions.ios

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

private const val FLOW_DELAY: Long = 50

// For iOS Unit Test
class AnyFlow<T>(source: Flow<T>): Flow<T> by source {

    constructor(result: T) :
        this(flow {
            delay(FLOW_DELAY)
            emit(result)
        })
    constructor(errorMessage: String) :
        this(flow {
            delay(FLOW_DELAY)
            error(errorMessage)
        })
}

object WrappedFlow {

    fun bool(bool: Boolean): Flow<Boolean> {
        return flow {
            delay(FLOW_DELAY)
            emit(bool)
        }
    }
    fun bool(errorMessage: String): Flow<Boolean> {
        return flow {
            delay(FLOW_DELAY)
            error(errorMessage)
        }
    }
}
