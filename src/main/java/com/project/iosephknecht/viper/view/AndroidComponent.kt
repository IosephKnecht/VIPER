package com.project.iosephknecht.viper.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager


interface AndroidComponent {
    val activityComponent: AppCompatActivity?
    val fragmentManagerComponent: FragmentManager?
}