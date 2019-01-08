package com.project.iosephknecht.viper.router

abstract class AbstractRouter<L : Router.Listener> : Router<L> {
    protected var routerListener: L? = null

    override fun setListener(listener: L) {
        this.routerListener = listener
    }

    override fun onDestroy() {
        routerListener = null
    }
}