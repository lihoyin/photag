package com.olux.photag.models.response

import com.olux.photag.models.User

data class AuthResponse(
    val user: User,
    val token: String,
    val isNewUser: Boolean
)