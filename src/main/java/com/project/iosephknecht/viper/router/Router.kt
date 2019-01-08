package com.project.iosephknecht.viper.router

interface Router<L : Router.Listener> {
    interface Listener

    fun setListener(listener: L)

    fun onDestroy()
}