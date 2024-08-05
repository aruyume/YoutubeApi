package com.example.youtubeapi.data.repositories

import com.example.youtubeapi.data.network.api.YoutubeApiService
import com.example.youtubeapi.data.repositories.base.BaseRepository
import com.example.youtubeapi.utils.Constants

class YoutubeRepository(
    private val apiService: YoutubeApiService
) : BaseRepository() {

    fun getPlaylists() = doRequest {
        apiService.getPlaylists(
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            apiKey = Constants.API_KEY,
            maxResults = Constants.MAX_RESULTS
        )
    }

    fun getPlaylistItems(playlistId: String) = doRequest {
        apiService.getPlaylistItems(
            part = Constants.PART,
            playlistId = playlistId,
            apiKey = Constants.API_KEY,
            maxResults = Constants.MAX_RESULTS
        )
    }
}