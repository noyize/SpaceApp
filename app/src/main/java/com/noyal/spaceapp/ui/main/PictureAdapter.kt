package com.noyal.spaceapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.noyal.spaceapp.data.Picture
import com.noyal.spaceapp.databinding.ItemNewsBinding
import kotlinx.coroutines.Dispatchers


class NewsAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Picture, RecyclerView.ViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsViewHolder) {
            holder.bind(getItem(position))
        }
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].url.hashCode().toLong()
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(picture: Picture) {
            binding.apply {
                newsThumbnail.load(picture.url){
                    dispatcher(Dispatchers.IO)
                    crossfade(true)
                }
                root.setOnClickListener { listener.onItemClick(adapterPosition) }
            }
        }

    }
}

object DiffCallback : DiffUtil.ItemCallback<Picture>() {
    override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem == newItem
    }
}

