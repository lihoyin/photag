package com.olux.photag.models

import com.google.gson.annotations.SerializedName

data class Photo (
    @SerializedName("_id") val id: String,
    val url: String,
    val width: Int,
    val height: Int
)