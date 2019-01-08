package com.project.iosephknecht.viper.presenter

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.project.iosephknecht.viper.removeObservers
import com.project.iosephknecht.viper.view.AndroidComponent

abstract class AbstractPresenter(application: Application) : AndroidViewModel(application), MvpPresenter {

    final override var androidComponent: AndroidComponent? = null
    private val observers = mutableListOf<LiveData<*>>()

    override fun attachAndroidComponent(androidComponent: AndroidComponent) {
        this.androidComponent = androidComponent
    }

    override fun detachAndroidComponent() {
        androidComponent = null
    }

    protected fun registerObservers(vararg liveData: LiveData<*>) {
        liveData.forEach {
            observers.add(it)
        }
    }

    protected fun unregisterObservers() {
        observers.forEach {
            it.removeObservers(androidComponent!!)
        }
        observers.clear()
    }
}