package com.example.youtubeapi.data.network.api

import com.example.youtubeapi.data.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {
    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String = "snippet,contentDetails",
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int = 20
    ): BaseResponse

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String = "snippet,contentDetails",
        @Query("playlistId") playlistId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int = 20
    ): BaseResponse

}