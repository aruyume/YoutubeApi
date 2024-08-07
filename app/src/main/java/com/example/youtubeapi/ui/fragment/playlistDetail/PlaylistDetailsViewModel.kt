package com.example.youtubeapi.ui.fragment.playlistDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.model.BaseResponse
import com.example.youtubeapi.data.repositories.YoutubeRepository
import com.example.youtubeapi.utils.Resource
import kotlinx.coroutines.launch

class PlaylistDetailsViewModel(private val repository: YoutubeRepository) : ViewModel() {

    private val _playlistDetails = MutableLiveData<Resource<BaseResponse>>()
    val playlistDetails: LiveData<Resource<BaseResponse>> get() = _playlistDetails

    fun getPlaylistDetails(playlistId: String) {
        viewModelScope.launch {
            repository.getPlaylistDetails(playlistId).observeForever {
                _playlistDetails.postValue(it)
            }
        }
    }
}