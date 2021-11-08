package com.moanes.flickrapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Photos")
data class Photo(
    @SerializedName("farm")
    var farm: Int=0,
    @PrimaryKey
    @SerializedName("id")
    var id: String,
    @SerializedName("isfamily")
    var isfamily: Int=0,
    @SerializedName("isfriend")
    var isfriend: Int=0,
    @SerializedName("ispublic")
    var ispublic: Int=0,
    @SerializedName("owner")
    var owner: String="",
    @SerializedName("secret")
    var secret: String="",
    @SerializedName("server")
    var server: String="",
    @SerializedName("title")
    var title: String=""
) {
    fun getUrl(): String {
        return "https://live.staticflickr.com/${server}/${id}_${secret}.jpg"
    }

}