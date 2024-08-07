package com.example.youtubeapi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.data.network.api.YoutubeApiService
import com.example.youtubeapi.utils.Constants
import retrofit2.HttpException
import java.io.IOException

class YoutubePagingSource(
    private val apiService: YoutubeApiService,
    private val playlistId: String
) : PagingSource<String, Item>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
        val pageToken = params.key ?: ""

        return try {
            val response = apiService.getPlaylistDetails(
                part = Constants.PART,
                playlistId = playlistId,
                apiKey = Constants.API_KEY,
                maxResults = Constants.MAX_RESULTS,
                pageToken = pageToken
            )

            LoadResult.Page(
                data = response.items,
                prevKey = null,
                nextKey = response.nextPageToken
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Item>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}