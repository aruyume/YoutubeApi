package com.example.youtubeapi.di

import com.example.youtubeapi.ui.fragment.playlist.PlaylistViewModel
import com.example.youtubeapi.ui.fragment.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule : Module = module {
    viewModel {
        VideoViewModel(get())
    }
    viewModel {
        PlaylistViewModel(get())
    }

}