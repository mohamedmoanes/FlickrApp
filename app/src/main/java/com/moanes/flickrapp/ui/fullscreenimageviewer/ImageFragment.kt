package com.moanes.flickrapp.ui.fullscreenimageviewer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ablanco.zoomy.Zoomy
import com.moanes.flickrapp.R
import com.moanes.flickrapp.utilities.extensions.setImageURL
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment(private val imageURL: String) : Fragment() {
    constructor() : this("")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image.setImageURL(imageURL)

        val builder: Zoomy.Builder = Zoomy.Builder(requireActivity()).target(image)
        builder.register()

    }

}