package com.moanes.flickrapp.module

import com.moanes.flickrapp.ui.PhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PhotosViewModel(get()) }
}