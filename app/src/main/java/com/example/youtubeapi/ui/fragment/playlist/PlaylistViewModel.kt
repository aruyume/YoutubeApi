package com.example.youtubeapi.ui.fragment.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.model.BaseResponse
import com.example.youtubeapi.data.repositories.YoutubeRepository
import com.example.youtubeapi.utils.Resource
import kotlinx.coroutines.launch

class PlaylistViewModel(private val repository: YoutubeRepository) : ViewModel() {

    private val _playlistVideos = MutableLiveData<Resource<BaseResponse>>()
    val playlistVideos: LiveData<Resource<BaseResponse>> get() = _playlistVideos

    fun getPlaylists(playlistId: String) {
        viewModelScope.launch {
            repository.getPlaylistItems(playlistId).observeForever {
                _playlistVideos.postValue(it)
            }
        }
    }
}