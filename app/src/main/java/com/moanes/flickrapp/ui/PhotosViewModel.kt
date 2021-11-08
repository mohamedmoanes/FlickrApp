package com.moanes.flickrapp.ui

import androidx.annotation.VisibleForTesting
import com.moanes.flickrapp.base.BaseViewModel
import com.moanes.flickrapp.data.repository.PhotosRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.fixedRateTimer

class PhotosViewModel(private val photosRepo: PhotosRepo) : BaseViewModel() {
    val photosLiveData = photosRepo.getLocalPhotos()
    private var mCurrentPage = 0
    private var mTotalPage = 1

    init {
        autoRefresh()
    }

    @VisibleForTesting
     fun getPhotos() = launch {
        try {
            showLoading.postValue(true)

            val result = withContext(Dispatchers.IO) {
                photosRepo.getRemoteData(mCurrentPage)
            }

            mCurrentPage = result.page
            mTotalPage = result.pages

            checkData()

            showLoading.postValue(false)

        } catch (exception: Exception) {
            showLoading.postValue(false)
            checkData()
            errorLiveData.postValue(
                exception.localizedMessage
            )
        }
    }

    private fun checkData() {
        if (mCurrentPage == 1)
            showNoData.postValue(photosLiveData.value.isNullOrEmpty())
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