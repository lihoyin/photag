package com.olux.photag.repositories.services

import com.olux.photag.models.request.AuthRequest
import com.olux.photag.models.response.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth")
    fun auth(@Body request: AuthRequest): Call<AuthResponse>
}