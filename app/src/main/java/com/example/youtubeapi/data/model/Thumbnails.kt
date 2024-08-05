package com.example.youtubeapi.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Thumbnails(
    @SerializedName("default")
    val default: Default,
    @SerializedName("high")
    val high: High,
    @SerializedName("medium")
    val medium: Medium
)