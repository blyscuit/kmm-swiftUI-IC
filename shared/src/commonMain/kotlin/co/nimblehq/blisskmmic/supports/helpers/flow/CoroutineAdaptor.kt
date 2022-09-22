package co.nimblehq.blisskmmic.supports.helpers.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

class AnyFlow<T>(source: Flow<T>): Flow<T> by source {
    val source: Flow<T> = this
}

class FlowAdapter<T : Any>(
    private val scope: CoroutineScope,
    private val flow: Flow<T>
) {
    fun subscribe(
        onEach: (item: T) -> Unit,
        onComplete: () -> Unit,
        onThrow: (error: Throwable) -> Unit
    ): Canceller = JobCanceller(
        flow.onEach { onEach(it) }
            .catch { onThrow(it) }
            .onCompletion { onComplete() }
            .launchIn(scope)
    )
}

interface Canceller {
    fun cancel()
}

private class JobCanceller(private val job: Job) : Canceller {
    override fun cancel() {
        job.cancel()
    }
}
