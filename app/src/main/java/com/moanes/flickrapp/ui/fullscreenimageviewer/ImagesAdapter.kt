package com.moanes.flickrapp.ui.fullscreenimageviewer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moanes.flickrapp.data.model.Photo

class ImagesAdapter(private val list: List<Photo>, fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return ImageFragment(list[position].getUrl())
    }
}