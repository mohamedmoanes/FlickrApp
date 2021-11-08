package com.moanes.flickrapp.data.repository

import androidx.lifecycle.LiveData
import com.moanes.flickrapp.data.db.PhotosDao
import com.moanes.flickrapp.data.model.Photo
import com.moanes.flickrapp.data.model.Photos
import com.moanes.flickrapp.data.network.Service

interface PhotosRepo {
    suspend fun getRemoteData(page: Int):Photos
    fun getLocalPhotos(): LiveData<List<Photo>>

   suspend fun clearPhotos()
}

class PhotosRepoImpl(private val remote: Service, private val dao: PhotosDao) : PhotosRepo {
    override suspend fun getRemoteData(page: Int):Photos {
        val response=remote.getPhotos(page, 20).photos
        dao.insertAll(response.photo)
        return response
    }

    override fun getLocalPhotos(): LiveData<List<Photo>> {
        return dao.getPhotos()
    }

    override suspend  fun clearPhotos() {
        dao.clearPhotos()
    }
}