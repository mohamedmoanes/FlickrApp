package com.moanes.flickrapp.data.network

import com.moanes.flickrapp.data.model.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("rest/?format=json&nojsoncallback=1&method=flickr.photos.search&tags=[trending,popular]")
    suspend fun getPhotos(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): PhotosResponse
}