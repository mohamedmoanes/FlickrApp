package com.moanes.flickrapp.utilities.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.moanes.flickrapp.R
import com.squareup.picasso.Picasso

fun AppCompatImageView.setImageURL(url: String, placeholder: Int= R.drawable.ic_photo) {
    Picasso.get().load(url).fit().placeholder(placeholder).error(R.drawable.ic_broken_image).into(this)
}