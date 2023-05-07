package com.troshchii.pixabay.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.troshchii.pixabay.R
import com.troshchii.pixabay.data.Hit
import com.troshchii.pixabay.databinding.ListItemBinding

class HitsAdapter : PagingDataAdapter<Hit, ViewHolder>(ItemsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(hit: Hit) {
        val context = binding.image.context

        Glide.with(context)
            .load(hit.previewURL)
            .into(binding.image)

        Glide.with(context)
            .load(hit.userImageURL)
            .into(binding.userAvatar)

        binding.user.text = hit.user
        binding.likes.text = context.getString(R.string.likes, hit.likes)
    }
}

class ItemsDiffCallback : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(oldItem: Hit, newItem: Hit) = (oldItem.id == newItem.id)
    override fun areContentsTheSame(oldItem: Hit, newItem: Hit) = (oldItem == newItem)
}
