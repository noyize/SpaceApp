package com.noyal.spaceapp.ui.details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.noyal.spaceapp.R
import com.noyal.spaceapp.data.News
import com.noyal.spaceapp.databinding.ItemDetailBinding
import kotlinx.coroutines.Dispatchers


class DetailAdapter(context: Context) :
    ListAdapter<News, RecyclerView.ViewHolder>(DiffCallback) {
    private val typeface = ResourcesCompat.getFont(context, R.font.bai_jam_bold)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.collapsingToolbarLayout.setCollapsedTitleTypeface(typeface)
        binding.collapsingToolbarLayout.setExpandedTitleTypeface(typeface)
        return DetailViewHolder(binding)


    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DetailViewHolder) {
            holder.bind(getItem(position))
        }
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].url.hashCode().toLong()
    }

    inner class DetailViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.apply {
                itemImage.load(news.hdUrl){
                    dispatcher(Dispatchers.IO)
                    crossfade(true)
                }

                collapsingToolbarLayout.title = news.title
                itemDate.text = news.date
                itemDescription.text = news.explanation
                news.copyright?.let {
                    itemCopyright.isVisible = true
                    itemCopyright.text = it
                }

            }
        }


    }
}

object DiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}

