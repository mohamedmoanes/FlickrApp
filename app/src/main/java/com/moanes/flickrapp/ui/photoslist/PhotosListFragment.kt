package com.moanes.flickrapp.ui.photoslist

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moanes.flickrapp.R
import com.moanes.flickrapp.base.BaseFragment
import com.moanes.flickrapp.ui.PhotosViewModel
import kotlinx.android.synthetic.main.fragment_photos_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class PhotosListFragment : BaseFragment() {
    private val viewModel: PhotosViewModel by sharedViewModel()
    private lateinit var adapter: PhotosAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_photos_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleError(viewModel)
        handleProgress(viewModel, refreshLayout)

        initPhotosList()

        handlePagination()

        handleRefreshLayout()

        handlePhotosLiveData()

        viewModel.loadNextPage()
    }



    private fun handlePhotosLiveData() {
        viewModel.photosLiveData.observe(viewLifecycleOwner, {
            adapter.submitList(it.toMutableList())
        })
    }

    private fun handleRefreshLayout(){
        refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initPhotosList() {
        adapter = PhotosAdapter(::openPhoto)
        val layoutManager=if(resources.configuration.orientation ==ORIENTATION_PORTRAIT)
            LinearLayoutManager(requireContext())
        else
            GridLayoutManager(requireActivity(),2)

        photoRv.layoutManager = layoutManager

        photoRv.adapter = adapter
    }

    private fun handlePagination() {
        photoRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager!!.childCount
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val firstVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModel.loadNextPage()
                }
            }
        })
    }

    private fun openPhoto(position:Int) {
        findNavController().navigate(PhotosListFragmentDirections.actionPhotosListFragmentToFullScreenImageFragment(position))
    }
}