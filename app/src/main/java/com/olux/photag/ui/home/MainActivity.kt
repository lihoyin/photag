package com.olux.photag.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.olux.photag.MyApp
import com.olux.photag.R
import com.olux.photag.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_PICK_PHOTO = 1
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply { lifecycleOwner = this@MainActivity }

        recyclerView.adapter = PhotosAdapter()
        swipeRefreshLayout.setOnRefreshListener(viewModel::retrievePhotoList)
        fab.setOnClickListener(viewModel::onSelectPhoto)
        btnLogout.setOnClickListener { MyApp.instance.logout() }

        viewModel.photos.observe(
            this,
            Observer { (recyclerView.adapter as PhotosAdapter).setPhotos(it) })
        viewModel.isLoading.observe(this, Observer { swipeRefreshLayout.isRefreshing = it })

        viewModel.retrievePhotoList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, photoReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, photoReturnedIntent)
        when (requestCode) {
            REQUEST_CODE_PICK_PHOTO -> if (resultCode == Activity.RESULT_OK) {
                val selectedPhoto: Uri? = photoReturnedIntent!!.data
                viewModel.doUploadPhoto(this, selectedPhoto!!)
            }
        }
    }
}
