package com.moanes.flickrapp.ui.photoslist

import com.moanes.flickrapp.BaseViewModel
import com.moanes.flickrapp.data.repository.PhotosRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotosLIstViewModel(private val photosRepo: PhotosRepo):BaseViewModel() {
val photosLiveData=photosRepo.getLocalPhotos()
    private var mCurrentPage = 0
    private var mTotalPage = 1

    fun getPhotos()=handleRequest {
        val result= withContext(Dispatchers.IO){
            photosRepo.getRemoteData(mCurrentPage)
        }
        mCurrentPage = result.page
        mTotalPage = result.pages
    }
    fun loadNextPage() {
        if (mCurrentPage < mTotalPage) {
            mCurrentPage++
            getPhotos()
        }
    }

    fun refresh() {
        mCurrentPage = 0
        loadNextPage()
    }

}