package com.example.youtubeapi.ui.fragment.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.model.BaseResponse
import com.example.youtubeapi.data.repositories.YoutubeRepository
import com.example.youtubeapi.utils.Resource
import kotlinx.coroutines.launch

class PlayerViewModel(private val repository: YoutubeRepository) : ViewModel() {

    private val _videos = MutableLiveData<Resource<BaseResponse>>()
    val videos: LiveData<Resource<BaseResponse>> get() = _videos

    fun getVideos(playlistId: String) {
        viewModelScope.launch {
            repository.getPlaylistDetails(playlistId).observeForever {
                _videos.postValue(it)
            }
        }
    }
}