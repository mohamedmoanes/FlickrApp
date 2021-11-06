package com.moanes.flickrapp.data.model

import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("page")
    var page: Int,
    @SerializedName("pages")
    var pages: Int,
    @SerializedName("perpage")
    var perpage: Int,
    @SerializedName("photo")
    var photo: List<Photo>,
    @SerializedName("total")
    var total: Int
)