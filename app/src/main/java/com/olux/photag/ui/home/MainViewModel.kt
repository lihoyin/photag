package com.olux.photag.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olux.photag.models.Photo
import com.olux.photag.repositories.ApiClient
import com.olux.photag.repositories.MyCallback
import com.olux.photag.utils.PhotoHelper
import com.olux.photag.utils.PrefHelper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody


class MainViewModel : ViewModel() {
    val photos = MutableLiveData<List<Photo>>()
    val isLoading = MutableLiveData(false)
    val isPhotoUploading = MutableLiveData(false)

    fun retrievePhotoList() {
        isLoading.value = true
        ApiClient.photoService.getPhotos().enqueue(MyCallback<List<Photo>> {
            photos.value = it
            isLoading.value = false
        })
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
            photos.value = mutableListOf(it).plus(photos.value!!)
        })
    }

    fun onSelectPhoto(view: View) = (view.context as Activity).startActivityForResult(
            Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            ), MainActivity.REQUEST_CODE_PICK_PHOTO
        )
}