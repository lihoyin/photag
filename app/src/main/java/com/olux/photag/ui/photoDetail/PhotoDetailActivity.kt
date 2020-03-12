package com.olux.photag.ui.photoDetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.olux.photag.R
import com.olux.photag.databinding.ActivityPhotoDetailBinding
import kotlinx.android.synthetic.main.activity_main.*

class PhotoDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PHOTO_ID = "EXTRA_PHOTO_ID"
    }

    private val viewModel: PhotoDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .setContentView<ActivityPhotoDetailBinding>(this, R.layout.activity_photo_detail)
            .apply {
                lifecycleOwner = this@PhotoDetailActivity
                viewModel = this@PhotoDetailActivity.viewModel
            }

        viewModel.retrievePhoto(intent.getStringExtra(EXTRA_PHOTO_ID))
    }
}
