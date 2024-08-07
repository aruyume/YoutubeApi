package com.example.youtubeapi.ui.fragment.player

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.youtubeapi.databinding.FragmentPlayerBinding
import com.example.youtubeapi.ui.fragment.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerFragment :
    BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    private val viewModel by viewModel<PlayerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

        val videoId = arguments?.getString("VIDEO_ID") ?: return
        viewModel.getVideos(videoId)
    }

    override fun setupClickListeners() {
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setupObservers() {
        viewModel.videos.resourceHandler(
            isLoading = { visibility ->
                binding.progressBar.isVisible = visibility
            },
            onSuccess = { data ->
                data?.let {
                    if (data.items.isNotEmpty()) {
                        val item = data.items[0]
                        binding.tvTitle.text = item.snippet.title
                        binding.tvDescription.text = item.snippet.description
                    }
                }
            },
            onError = { error ->
                binding.progressBar.isVisible = false
            }
        )
    }
}