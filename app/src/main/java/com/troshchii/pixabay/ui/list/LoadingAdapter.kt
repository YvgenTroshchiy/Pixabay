package com.troshchii.pixabay.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.troshchii.pixabay.databinding.ListItemLoadingBinding

class LoadingAdapter : LoadStateAdapter<LoadingAdapter.StateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StateItemViewHolder {
        val binding =
            ListItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StateItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StateItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class StateItemViewHolder(
        private val binding: ListItemLoadingBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}
