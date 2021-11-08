package com.moanes.flickrapp.ui.fullscreenimageviewer

import com.moanes.flickrapp.base.BaseViewModel
import com.moanes.flickrapp.data.repository.PhotosRepo

class FullScreenImageViewModel(private val photosRepo: PhotosRepo): BaseViewModel() {

    val photosLiveData = photosRepo.getLocalPhotos()

}