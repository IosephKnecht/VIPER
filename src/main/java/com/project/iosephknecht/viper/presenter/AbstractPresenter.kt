package com.project.iosephknecht.viper.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.iosephknecht.viper.removeObservers
import com.project.iosephknecht.viper.view.AndroidComponent

abstract class AbstractPresenter : ViewModel(), MvpPresenter {

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