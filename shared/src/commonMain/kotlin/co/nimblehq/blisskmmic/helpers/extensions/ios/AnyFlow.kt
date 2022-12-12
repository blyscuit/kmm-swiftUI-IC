package co.nimblehq.blisskmmic.helpers.extensions.ios

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

const val anyFlowDefaultDelay: Long = 50

// For iOS Unit Test
class AnyFlow<T>(source: Flow<T>): Flow<T> by source {
    constructor(result: T, delay: Long) :
        this(flow {
            delay(delay)
            emit(result)
        })

    constructor(errorMessage: String, delay: Long) :
        this(flow {
            delay(delay)
            error(errorMessage)
        })
    
    constructor(result: T) : this(result, anyFlowDefaultDelay)
    constructor(errorMessage: String) : this(errorMessage, anyFlowDefaultDelay)
}

object WrappedFlow {

    fun bool(bool: Boolean, delay: Long = 50): Flow<Boolean> {
        return flow {
            delay(delay)
            emit(bool)
        }
    }

    fun bool(errorMessage: String, delay: Long = 50): Flow<Boolean> {
        return flow {
            delay(delay)
            error(errorMessage)
        }
    }

    fun bool(bool: Boolean): Flow<Boolean> {
        return bool(bool, anyFlowDefaultDelay)
    }

    fun bool(errorMessage: String): Flow<Boolean> {
        return bool(errorMessage, anyFlowDefaultDelay)
    }
}
