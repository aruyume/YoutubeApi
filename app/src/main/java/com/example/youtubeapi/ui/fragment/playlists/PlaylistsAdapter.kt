package com.example.youtubeapi.ui.fragment.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.databinding.ItemPlaylistsBinding

class PlaylistsAdapter(private val onItemClick: (Item) -> Unit) :
    ListAdapter<Item, PlaylistsAdapter.PlaylistsViewHolder>(PlaylistsItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        val binding =
            ItemPlaylistsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PlaylistsViewHolder(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) = with(binding) {
            tvTitle.text = item.snippet.title
            tvSeries.text = "${item.contentDetails.itemCount} video series"

            Glide.with(binding.root)
                .load(item.snippet.thumbnails.high.url)
                .into(imgVideo)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}

class PlaylistsItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}