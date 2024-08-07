package com.example.youtubeapi.ui.fragment.playlists

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.R
import com.example.youtubeapi.databinding.FragmentPlaylistsBinding
import com.example.youtubeapi.ui.fragment.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistsFragment :
    BaseFragment<FragmentPlaylistsBinding>(FragmentPlaylistsBinding::inflate) {

    private val viewModel by viewModel<PlaylistsViewModel>()
    private val playlistsAdapter: PlaylistsAdapter by lazy {
        PlaylistsAdapter { playlists ->
            navigateToPlaylistDetailsFragment(playlists.id)
        }
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        viewModel.getPlaylists()
    }

    override fun setupObservers() {
        viewModel.playlists.resourceHandler(
            isLoading = { visibility ->
                binding.progressBar.isVisible = visibility
            },
            onSuccess = { data ->
                playlistsAdapter.submitList(data!!.items)
            }
        )
    }

    private fun setupRecyclerView() = with(binding.rvVideo) {
        layoutManager = LinearLayoutManager(context)
        adapter = playlistsAdapter
    }

    private fun navigateToPlaylistDetailsFragment(videoId: String) {
        val bundle = Bundle().apply {
            putString("VIDEO_ID", videoId)
        }
        findNavController().navigate(
            R.id.action_playlistsFragment_to_playlistDetailsFragment,
            bundle
        )
    }
}