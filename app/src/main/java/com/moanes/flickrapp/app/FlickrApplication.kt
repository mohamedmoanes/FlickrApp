package com.moanes.flickrapp.app

import android.app.Application
import android.content.Context
import com.moanes.flickrapp.module.appModules
import com.moanes.flickrapp.module.databaseModule
import com.moanes.flickrapp.module.repoModule
import com.moanes.flickrapp.module.viewModelModule
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class FlickrApplication:Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        initKoin()
        initPicassoCashing()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@FlickrApplication)
            modules(listOf(appModules, databaseModule, repoModule, viewModelModule))
        }
    }

    private fun initPicassoCashing() {
        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, Long.MAX_VALUE))
        val built = builder.build()
        built.setIndicatorsEnabled(true)
        built.isLoggingEnabled = true
        Picasso.setSingletonInstance(built)
    }
}