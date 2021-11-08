package com.moanes.flickrapp.module

import com.moanes.flickrapp.ui.fullscreenimageviewer.FullScreenImageViewModel
import com.moanes.flickrapp.ui.photoslist.PhotosListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PhotosListViewModel(get()) }
    viewModel { FullScreenImageViewModel(get()) }
}