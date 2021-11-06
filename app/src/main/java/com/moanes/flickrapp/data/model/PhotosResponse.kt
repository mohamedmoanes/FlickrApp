package com.moanes.flickrapp.data.model

import com.google.gson.annotations.SerializedName

data class PhotosResponse(@SerializedName("photos")
                          var photos: Photos,
                          @SerializedName("stat")
                          var stat: String)
