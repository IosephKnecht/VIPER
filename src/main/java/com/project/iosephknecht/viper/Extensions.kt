package com.project.iosephknecht.viper

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.project.iosephknecht.viper.view.AbstractActivity
import com.project.iosephknecht.viper.view.AbstractFragment
import com.project.iosephknecht.viper.view.AndroidComponent

fun viewModelProvider(androidComponent: AndroidComponent): ViewModelProvider {
    return viewModelProvider(androidComponent, null)
}

fun viewModelProvider(androidComponent: AndroidComponent,
                      factory: ViewModelProvider.Factory?): ViewModelProvider {
    return when (androidComponent) {
        is AbstractActivity<*> -> {
            ViewModelProviders.of(androidComponent, factory)
        }
        is AbstractFragment<*> -> {
            ViewModelProviders.of(androidComponent, factory)
        }
        else -> {
            throw IllegalArgumentException("android component not exist not null values")
        }
    }
}

fun <T> LiveData<T>.observe(androidComponent: AndroidComponent, block: (T?) -> Unit) {
    androidComponent.activityComponent
        ?.run {
            this@observe.observe({ this.lifecycle }, block)
        } ?: throw IllegalArgumentException("android component not exist not null values")
}

fun <T> LiveData<T>.removeObservers(androidComponent: AndroidComponent) {
    androidComponent.activityComponent?.let {
        this.removeObservers { it.lifecycle }
    } ?: throw IllegalArgumentException("android component not exist not null values")
}