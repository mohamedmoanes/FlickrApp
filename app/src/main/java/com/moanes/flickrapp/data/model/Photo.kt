package com.moanes.flickrapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Photos")
data class Photo(
    @SerializedName("farm")
    var farm: Int,
    @PrimaryKey
    @SerializedName("id")
    var id: String,
    @SerializedName("isfamily")
    var isfamily: Int,
    @SerializedName("isfriend")
    var isfriend: Int,
    @SerializedName("ispublic")
    var ispublic: Int,
    @SerializedName("owner")
    var owner: String,
    @SerializedName("secret")
    var secret: String,
    @SerializedName("server")
    var server: String,
    @SerializedName("title")
    var title: String
)