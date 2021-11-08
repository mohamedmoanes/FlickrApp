package com.moanes.flickrapp.app

import android.app.Application
import android.content.Context
import com.moanes.flickrapp.module.appModules
import com.moanes.flickrapp.module.databaseModule
import com.moanes.flickrapp.module.repoModule
import com.moanes.flickrapp.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FlickrApplication:Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        startKoin {
            androidContext(this@FlickrApplication)
            modules(listOf(appModules,databaseModule, repoModule, viewModelModule))
        }

    }
}