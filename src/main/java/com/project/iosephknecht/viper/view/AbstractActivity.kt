package com.project.iosephknecht.viper.view

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.project.iosephknecht.viper.presenter.MvpPresenter

abstract class AbstractActivity<P : MvpPresenter> : AppCompatActivity(), AndroidComponent {

    lateinit var presenter: P

    override val activityComponent: AppCompatActivity
        get() = this

    override val fragmentManagerComponent: FragmentManager?
        get() = this.supportFragmentManager

    abstract fun inject()

    abstract fun providePresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        presenter = providePresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachAndroidComponent(this)
    }

    override fun onStop() {
        presenter.detachAndroidComponent()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}