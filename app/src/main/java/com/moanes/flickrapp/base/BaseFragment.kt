package com.moanes.flickrapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    @LayoutRes
    abstract fun getLayout(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(getLayout(), container, false)
    }

    open fun showLoading() {
        (context as BaseActivity).showLoading()
    }

    open fun hideLoading() {
        (context as BaseActivity).hideLoading()
    }

    open fun showError(@StringRes message: Int) {
        (context as BaseActivity).showErrorToast(message)
    }

    open fun showError(message: String) {
        (context as BaseActivity).showErrorToast(message)
    }

    fun handleProgress(viewModel: BaseViewModel, swipeRefreshLayout: SwipeRefreshLayout? = null) {
        viewModel.showLoading.observe(viewLifecycleOwner, {

            swipeRefreshLayout?.let { swipeRefreshLayout ->
                swipeRefreshLayout.isRefreshing = it
            }

            if (it)
                showLoading()
            else
                hideLoading()
        })
    }

    fun handleError(viewModel: BaseViewModel) {
        viewModel.errorLiveData.observe(viewLifecycleOwner, {
            view?.let { it1 -> Snackbar.make(it1, it, Snackbar.LENGTH_SHORT).show() }
        })
    }

    fun handleNoData(viewModel: BaseViewModel, noData: View) {
        viewModel.showNoData.observe(viewLifecycleOwner, {
            if (it)
                noData.visibility = View.VISIBLE
            else
                noData.visibility = View.GONE
        })
    }
}