package com.olux.photag.ui.editPhoto

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.olux.photag.R
import com.olux.photag.databinding.ActivityEditPhotoBinding

class EditPhotoActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PHOTO_ID = "EXTRA_PHOTO_ID"
        const val REQUEST_CODE_PICK_PHOTO = 1
    }

    private val viewModel: EditPhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .setContentView<ActivityEditPhotoBinding>(
                this,
                R.layout.activity_edit_photo
            ).apply {
                lifecycleOwner = this@EditPhotoActivity
                viewModel = this@EditPhotoActivity.viewModel
            }

        viewModel.initPhoto(intent.getStringExtra(EXTRA_PHOTO_ID))
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
