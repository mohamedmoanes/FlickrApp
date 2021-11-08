package com.moanes.flickrapp.ui

import com.moanes.flickrapp.base.BaseViewModel
import com.moanes.flickrapp.data.repository.PhotosRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.concurrent.fixedRateTimer

class PhotosViewModel(private val photosRepo: PhotosRepo) : BaseViewModel() {
    val photosLiveData = photosRepo.getLocalPhotos()
    private var mCurrentPage = 0
    private var mTotalPage = 1

    init {
        autoRefresh()
    }

    private fun getPhotos() = handleRequest {
        val result = withContext(Dispatchers.IO) {
            photosRepo.getRemoteData(mCurrentPage)
        }
        if (mCurrentPage == 1)
            showNoData.postValue(photosLiveData.value.isNullOrEmpty())
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
        clearPhotos()
        loadNextPage()
    }

    private fun clearPhotos() = handleRequest {
        withContext(Dispatchers.IO) {
            (photosRepo.clearPhotos())
        }
    }

    private fun autoRefresh() {
        fixedRateTimer("timer", false, 0L, 60 * 1000) {
            mCurrentPage = 1
            getPhotos()
        }
    }


}