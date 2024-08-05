package com.example.youtubeapi.ui.fragment.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.model.BaseResponse
import com.example.youtubeapi.data.repositories.YoutubeRepository
import com.example.youtubeapi.utils.Resource
import kotlinx.coroutines.launch

class VideoViewModel(private val repository: YoutubeRepository) : ViewModel() {

    private val _playlists = MutableLiveData<Resource<BaseResponse>>()
    val playlists: LiveData<Resource<BaseResponse>> get() = _playlists

    fun getPlaylists() {
        viewModelScope.launch {
            repository.getPlaylists().observeForever {
                _playlists.postValue(it)
            }
        }
    }
}