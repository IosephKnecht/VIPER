package com.project.iosephknecht.viper.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.project.iosephknecht.viper.presenter.MvpPresenter

abstract class AbstractFragment<P : MvpPresenter> : Fragment(), AndroidComponent {
    lateinit var presenter: P

    override val activityComponent: AppCompatActivity
        get() = this.activity as AppCompatActivity

    override val fragmentManagerComponent: FragmentManager?
        get() = fragmentManager

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
}