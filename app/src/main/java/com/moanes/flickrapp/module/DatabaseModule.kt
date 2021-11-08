package com.moanes.flickrapp.module

import android.app.Application
import androidx.room.Room
import com.moanes.flickrapp.data.db.PhotosDao
import com.moanes.flickrapp.data.db.PhotosDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): PhotosDataBase {
        return Room.databaseBuilder(application, PhotosDataBase::class.java, "photos")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: PhotosDataBase): PhotosDao {
        return  database.photosDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }


}