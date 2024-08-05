package com.example.youtubeapi.ui.fragment.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.databinding.ItemPlaylistBinding

class PlaylistAdapter : ListAdapter<Item, PlaylistAdapter.PlaylistViewHolder>(PlaylistItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(video: Item) = with(binding) {
            tvTitle.text = video.snippet.title

            Glide.with(binding.root)
                .load(video.snippet.thumbnails.high.url)
                .into(imgPlaylist)
        }
    }
}

class PlaylistItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}