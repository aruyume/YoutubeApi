package com.example.youtubeapi.ui.fragment.playlistDetail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.R
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.databinding.FragmentPlaylistDetailsBinding
import com.example.youtubeapi.ui.fragment.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistDetailsFragment :
    BaseFragment<FragmentPlaylistDetailsBinding>(FragmentPlaylistDetailsBinding::inflate) {

    private val viewModel by viewModel<PlaylistDetailsViewModel>()
    private val playlistDetailsAdapter: PlaylistDetailsAdapter by lazy {
        PlaylistDetailsAdapter { item ->
            navigateToPlayerFragment(item.id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

        val videoId = arguments?.getString("VIDEO_ID") ?: return
        viewModel.getPlaylistDetails(videoId)
    }

    override fun setupClickListeners() {
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initViews() {
        setupRecyclerView()
    }

    override fun setupObservers() {
        viewModel.playlistDetails.resourceHandler(
            isLoading = { visibility ->
                binding.progressBar.isVisible = visibility
            },
            onSuccess = { data ->
                data?.let {
                    playlistDetailsAdapter.submitList(data.items)
                    if (data.items.isNotEmpty()) {
                        val item = data.items[0]
                        binding.tvTitle.text = item.snippet.title
                        binding.tvDescription.text = item.snippet.description
                        binding.tvVideoCount.text = "${data.items.size} video series"
                    }
                }
            }
        )
    }

    private fun setupRecyclerView() = with(binding.rvVideo) {
        layoutManager = LinearLayoutManager(context)
        adapter = playlistDetailsAdapter
    }

    private fun navigateToPlayerFragment(videoId: String) {
        val bundle = Bundle().apply {
            putString("VIDEO_ID", videoId)
        }
        findNavController().navigate(
            R.id.action_playlistDetailsFragment_to_playerFragment,
            bundle
        )
    }
}