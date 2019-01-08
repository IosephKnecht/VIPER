package com.project.iosephknecht.viper.interacor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class AbstractInteractor<L : MvpInteractor.Listener> : MvpInteractor<L> {
    private var listener: L? = null

    override fun setListener(listener: L?) {
        this.listener = listener
    }

    override fun onDestroy() {
        listener = null
    }

    protected fun <R, O : Observable<R>> discardResult(observable: O,
                                                       block: (listener: L?, result: PendingResult<R>) -> Unit): Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { block(listener, PendingResult(null, it)) }
            .subscribe({ block(listener, PendingResult(it, null)) },
                { it.printStackTrace() })
    }

    data class PendingResult<R>(
        val data: R?,
        val throwable: Throwable?
    )
}