package com.project.iosephknecht.viper.interacor

import kotlinx.coroutines.*

abstract class AbstractInteractor<L : MvpInteractor.Listener> : MvpInteractor<L> {
    private var listener: L? = null
    private val interactorScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    protected fun getListener() = listener

    override fun setListener(listener: L?) {
        this.listener = listener
    }

    override fun onDestroy() {
        listener = null
        interactorScope.cancel()
    }

    protected fun <R> discardResult(asyncBlock: suspend CoroutineScope.() -> R,
                                    resultBlock: (listener: L?, result: PendingResult<R>) -> Unit) {
        interactorScope.launch {
            val result: PendingResult<R> = try {
                val data = asyncBlock.invoke(this)
                PendingResult(data = data)
            } catch (throwable: Throwable) {
                PendingResult(throwable = throwable)
            }

            withContext(Dispatchers.Main) {
                resultBlock.invoke(listener, result)
            }
        }
    }

    data class PendingResult<R>(
        val data: R? = null,
        val throwable: Throwable? = null
    )
}
