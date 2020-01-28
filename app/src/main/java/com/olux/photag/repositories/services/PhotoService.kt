package com.olux.photag.repositories.services

import com.olux.photag.models.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoService {
    @GET("photos")
    fun getPhotos(): Call<List<Photo>>

    @GET("photos/{id}")
    fun getPlace(@Path("id") id: String): Call<Photo>
}