package com.example.youtubeapi.ui.fragment.playlist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.databinding.FragmentPlaylistBinding
import com.example.youtubeapi.ui.fragment.base.BaseFragment
import com.example.youtubeapi.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistFragment : BaseFragment<FragmentPlaylistBinding>(FragmentPlaylistBinding::inflate) {

    private val viewModel by viewModel<PlaylistViewModel>()
    private val playlistAdapter: PlaylistAdapter by lazy { PlaylistAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()

        val videoId = arguments?.getString("VIDEO_ID") ?: return
        viewModel.getPlaylists(videoId)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setupObservers() {
        viewModel.playlistVideos.resourceHandler(
            onSuccess = { data ->
                playlistAdapter.submitList(data.items)
                if (data.items.isNotEmpty()) {
                    val item = data.items[0]
                    binding.tvTitle.text = item.snippet.title
                    binding.tvDescription.text = item.snippet.description
                    binding.tvVideoCount.text = "${data.items.size} video series"
                }
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )
    }

    private fun setupRecyclerView() = with(binding.rvVideo) {
        layoutManager = LinearLayoutManager(context)
        adapter = playlistAdapter
    }
}