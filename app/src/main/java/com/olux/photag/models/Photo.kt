package com.olux.photag.models

import com.google.gson.annotations.SerializedName

data class Photo (
    @SerializedName("_id") val id: String? = null,
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)