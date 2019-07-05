package com.project.iosephknecht.viper.presenter

import com.project.iosephknecht.viper.view.AndroidComponent

interface MvpPresenter {
    var androidComponent: AndroidComponent?

    fun attachAndroidComponent(androidComponent: AndroidComponent)

    fun detachAndroidComponent()

    fun onDestroy()
}