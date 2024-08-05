package com.example.youtubeapi.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Snippet(
    @SerializedName("country")
    val country: String,
    @SerializedName("customUrl")
    val customUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("localized")
    val localized: Localized,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("thumbnails")
    val thumbnails: Thumbnails,
    @SerializedName("title")
    val title: String
)