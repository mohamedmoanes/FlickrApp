package com.moanes.flickrapp.module

import com.moanes.flickrapp.data.repository.PhotosRepo
import com.moanes.flickrapp.data.repository.PhotosRepoImpl
import org.koin.dsl.module

val repoModule = module {
    factory<PhotosRepo> { PhotosRepoImpl(get(),get()) }
}