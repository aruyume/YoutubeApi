package com.example.youtubeapi.di

import com.example.youtubeapi.ui.fragment.player.PlayerViewModel
import com.example.youtubeapi.ui.fragment.playlistDetail.PlaylistDetailsViewModel
import com.example.youtubeapi.ui.fragment.playlists.PlaylistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel {
        PlaylistsViewModel(get())
    }
    viewModel {
        PlaylistDetailsViewModel(get())
    }
    viewModel {
        PlayerViewModel(get())
    }
}