package com.moanes.flickrapp.ui.fullscreenimageviewer

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.moanes.flickrapp.R
import com.moanes.flickrapp.base.BaseFragment
import com.moanes.flickrapp.data.model.Photo
import com.moanes.flickrapp.ui.PhotosViewModel
import com.moanes.flickrapp.utilities.extensions.setImageURL
import kotlinx.android.synthetic.main.fragment_full_screen_image.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FullScreenImageFragment : BaseFragment() {
    private val viewModel: PhotosViewModel by sharedViewModel()
    private val args: FullScreenImageFragmentArgs by navArgs()

    override fun getLayout(): Int {
        return R.layout.fragment_full_screen_image
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
handlePhotosLiveData()
        close()
    }
    private fun handlePhotosLiveData() {
        viewModel.photosLiveData.observe(viewLifecycleOwner, {
            initImagesSlider(it)
        })
    }
    private fun initImagesSlider(list: List<Photo>) {
        counter.text = "${args.position} / ${list.size}"
        viewPager.adapter = ImagesAdapter(list, childFragmentManager, lifecycle)
        viewPager.setCurrentItem(args.position, true)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                background_image_view.setImageURL(list[position].getUrl())
                counter.text = "${position + 1} / ${list.size}"
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

    private fun close() {
        closeBTN.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}