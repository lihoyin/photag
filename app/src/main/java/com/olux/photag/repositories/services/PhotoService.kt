package com.olux.photag.repositories.services

import com.olux.photag.models.Photo
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface PhotoService {
    @GET("photos")
    fun getPhotos(): Call<List<Photo>>

    @GET("photos/{id}")
    fun getPhoto(@Path("id") id: String): Call<Photo>

    @Multipart
    @POST("photos")
    fun uploadPhoto(@Part photoFile: MultipartBody.Part): Call<Photo>
}