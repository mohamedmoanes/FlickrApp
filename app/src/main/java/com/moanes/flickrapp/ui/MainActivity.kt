package com.moanes.flickrapp.ui

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.moanes.flickrapp.R
import com.moanes.flickrapp.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var navController: NavController

    override fun getLayout(): Int {
        return R.layout.activity_main
    }


    private fun initNavController() {
        navController = findNavController(R.id.auth_host_fragment)
    }

    override fun init() {
        initNavController()
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.photosListFragment)
            super.onBackPressed()
        else
            navController.navigateUp()
    }
}