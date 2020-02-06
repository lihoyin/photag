package com.olux.photag.ui.editPhoto

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olux.photag.models.Photo
import com.olux.photag.repositories.ApiClient
import com.olux.photag.repositories.MyCallback
import com.olux.photag.utils.PhotoHelper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody


class EditPhotoViewModel : ViewModel() {
    val photo = MutableLiveData<Photo>()
    val isPhotoUploading = MutableLiveData(false)

    fun initPhoto(id: String?) {
        if (id == null) {
            photo.value = Photo()
        } else {
            ApiClient.photoService.getPhoto(id).enqueue(MyCallback<Photo> {
                photo.value = it
            })
        }
    }

    fun doUploadPhoto(context: Context, uri: Uri) {
        isPhotoUploading.value = true

        val requestBody = RequestBody.create(
            MediaType.parse("multipart/form-data"),
            PhotoHelper.getBitmap(context, uri)
        )
        val part = MultipartBody.Part.createFormData("photo", "image.jpeg", requestBody)

        ApiClient.photoService.uploadPhoto(part).enqueue(MyCallback<Photo> {
            isPhotoUploading.value = false
            photo.value = it
        })
    }

    fun onSelectPhoto(view: View) = (view.context as Activity).startActivityForResult(
        Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ), EditPhotoActivity.REQUEST_CODE_PICK_PHOTO
    )

//    fun onSubmit(view: View) =
//        ApiClient.photoService.createPlace(photo.value!!).enqueue(MyCallback<Photo> {
//            (view.context as Activity).finish()
//        })
}