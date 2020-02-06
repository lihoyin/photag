package com.olux.photag.repositories

import com.olux.photag.repositories.services.PhotoService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://192.168.1.74:3000"

object ApiClient {
    private val okHttpClient = OkHttpClient
        .Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val photoService: PhotoService by lazy {
        retrofit.create(PhotoService::class.java)
    }

}