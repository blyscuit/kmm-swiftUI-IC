package co.nimblehq.blisskmmic.helpers.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val FLOW_DELAY: Long = 50

fun <T> delayFlowOf(value: T) = flow {
    delay(FLOW_DELAY)
    emit(value)
}

fun <T> delayFlowOf(error: String): Flow<T> = flow {
    delay(FLOW_DELAY)
    error(error)
}
