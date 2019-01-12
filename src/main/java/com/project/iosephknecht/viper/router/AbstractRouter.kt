package com.project.iosephknecht.viper.router

abstract class AbstractRouter<L : MvpRouter.Listener> : MvpRouter<L> {
    protected var routerListener: L? = null

    override fun setListener(listener: L) {
        this.routerListener = listener
    }

    override fun onDestroy() {
        routerListener = null
    }
}