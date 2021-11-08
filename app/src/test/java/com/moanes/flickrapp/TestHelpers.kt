package com.moanes.flickrapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moanes.flickrapp.data.model.Photo


    fun getPhotos(): LiveData<List<Photo>> {
        val list = ArrayList<Photo>()
        for (i in 1..10) {
            val article = Photo(id = i.toString(),)
            list.add(article)
        }
        val liveData = MutableLiveData<List<Photo>>()
        liveData.value = list
        return liveData
    }
    fun getPhotosEmpty(): LiveData<List<Photo>> {
        val liveData = MutableLiveData<List<Photo>>()
        return liveData
    }
