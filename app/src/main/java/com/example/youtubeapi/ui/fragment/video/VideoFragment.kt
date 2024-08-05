package com.example.youtubeapi.ui.fragment.video

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.R
import com.example.youtubeapi.databinding.FragmentVideoBinding
import com.example.youtubeapi.ui.fragment.base.BaseFragment
import com.example.youtubeapi.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoFragment : BaseFragment<FragmentVideoBinding>(FragmentVideoBinding::inflate) {

    private val viewModel by viewModel<VideoViewModel>()
    private val videoAdapter: VideoAdapter by lazy { VideoAdapter { video ->
        navigateToPlaylistFragment(video.id)
    } }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        viewModel.getPlaylists()
    }

    override fun setupObservers() {
        viewModel.playlists.resourceHandler(
            onSuccess = { data ->
                videoAdapter.submitList(data.items)
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )
    }

    private fun setupRecyclerView() = with(binding.rvVideo) {
        layoutManager = LinearLayoutManager(context)
        adapter = videoAdapter
    }

    private fun navigateToPlaylistFragment(videoId: String) {
        val bundle = Bundle().apply {
            putString("VIDEO_ID", videoId)
        }
        findNavController().navigate(R.id.action_videoFragment_to_playlistFragment, bundle)
    }
}