package com.example.youtubeapi.ui.fragment.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.databinding.ItemVideoBinding

class VideoAdapter(private val onItemClick: (Item) -> Unit) :
    ListAdapter<Item, VideoAdapter.VideoViewHolder>(VideoItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VideoViewHolder(private val binding: ItemVideoBinding) :
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

class VideoItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}