package com.example.youtubeapi.ui.fragment.playlistDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.databinding.ItemPlaylistDetailsBinding

class PlaylistDetailsAdapter(private val onItemClick: (Item) -> Unit) :
    ListAdapter<Item, PlaylistDetailsAdapter.PlaylistDetailsViewHolder>(PlaylistDetailsItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistDetailsViewHolder {
        val binding =
            ItemPlaylistDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistDetailsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class PlaylistDetailsViewHolder(private val binding: ItemPlaylistDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(video: Item) = with(binding) {
            tvTitle.text = video.snippet.title

            Glide.with(binding.root)
                .load(video.snippet.thumbnails.high.url)
                .into(imgPlaylist)

            binding.root.setOnClickListener {
                onItemClick(video)
            }
        }
    }
}

class PlaylistDetailsItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}