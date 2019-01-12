package com.project.iosephknecht.viper.router

interface MvpRouter<L : MvpRouter.Listener> {
    interface Listener

    fun setListener(listener: L)

    fun onDestroy()
}