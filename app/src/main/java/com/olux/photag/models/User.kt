package com.olux.photag.models

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("_id") val id: String,
    val name: String,
    val picture: String
)