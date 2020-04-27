package com.therafaelreis.flowsample.presentation.view.application

import android.app.Application
import android.content.Context
import networkModules
import repositoryModules
import useCaseModules
import viewModelModules

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleApplication : Application(){

    companion object{
        lateinit var context :Context
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@SampleApplication)
            modules(listOf(repositoryModules, networkModules, useCaseModules, viewModelModules))
        }
    }
}