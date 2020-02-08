package com.olux.photag.ui.photoDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olux.photag.models.Photo
import com.olux.photag.repositories.ApiClient
import com.olux.photag.repositories.MyCallback


class PhotoDetailViewModel : ViewModel() {
    val photo = MutableLiveData<Photo>()
    val isLoading = MutableLiveData(false)

    fun retrievePhoto(id: String) {
        isLoading.value = true
        ApiClient.photoService.getPhoto(id).enqueue(MyCallback<Photo> {
            photo.value = it
            isLoading.value = false
        })
    }
}