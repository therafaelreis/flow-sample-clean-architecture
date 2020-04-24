package com.therafaelreis.flowsample.presentation

import android.app.Application
import android.content.Context
import mNetworkModules
import mRepositoryModules
import mUseCaseModules
import mViewModels

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
            modules(listOf(mRepositoryModules, mNetworkModules, mUseCaseModules, mViewModels))
        }
    }
}