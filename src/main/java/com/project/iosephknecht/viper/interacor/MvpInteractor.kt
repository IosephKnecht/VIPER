package com.project.iosephknecht.viper.interacor

interface MvpInteractor<L : MvpInteractor.Listener> {
    interface Listener

    fun setListener(listener: L?)
    fun onDestroy()
}