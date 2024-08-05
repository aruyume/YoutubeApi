package com.example.youtubeapi.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class High(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)