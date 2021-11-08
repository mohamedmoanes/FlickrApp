package com.moanes.flickrapp.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moanes.flickrapp.data.model.Photo

@Dao
interface PhotosDao {
    /** Get photos from local DB without pagination */
    @Query("Select * from Photos")
    fun getPhotos() : LiveData<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photoList: List<Photo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    /** Get photos from local DB with pagination */
    @Query("SELECT * FROM Photos")
    fun getPagedPhotos(): DataSource.Factory<Int, Photo>
}