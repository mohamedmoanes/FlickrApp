package com.moanes.flickrapp.ui.photoslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.moanes.flickrapp.R
import com.moanes.flickrapp.data.model.Photo
import com.moanes.flickrapp.utilities.extensions.setImageURL
import android.view.animation.Animation
import android.view.animation.AnimationUtils


class PhotosAdapter(private val click: (photo: Photo) -> Unit) :
    ListAdapter<Photo, PhotosAdapter.ViewHolder>(PhotoItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(holder.bindingAdapterPosition)
        holder.image.setImageURL("https://live.staticflickr.com/${item.server}/${item.id}_${item.secret}.jpg")

        holder.itemView.setOnClickListener {
            click(item)
        }
        val animation: Animation =
            AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)
        holder.itemView.startAnimation(animation)
    }

    class PhotoItemDiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(
            oldItem: Photo,
            newItem: Photo
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Photo,
            newItem: Photo
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ShapeableImageView = view.findViewById(R.id.image)
    }
}