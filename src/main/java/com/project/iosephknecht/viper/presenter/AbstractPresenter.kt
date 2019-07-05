package com.project.iosephknecht.viper.presenter

import androidx.lifecycle.ViewModel
import com.project.iosephknecht.viper.view.AndroidComponent

abstract class AbstractPresenter : ViewModel(), MvpPresenter {

    final override var androidComponent: AndroidComponent? = null

    override fun attachAndroidComponent(androidComponent: AndroidComponent) {
        this.androidComponent = androidComponent
    }

    override fun detachAndroidComponent() {
        androidComponent = null
    }

    override fun onCleared() {
        onDestroy()
        super.onCleared()
    }
}