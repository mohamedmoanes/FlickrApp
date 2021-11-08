package com.moanes.flickrapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moanes.flickrapp.data.model.Photo

@Database(
    entities = [Photo::class],
    version = 1, exportSchema = false
)
@TypeConverters()
abstract class PhotosDataBase: RoomDatabase() {
    abstract val photosDao:PhotosDao
}