package com.example.android.sameer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.sameer.databinding.GridItemBinding
import com.example.android.sameer.network.Images


class PhotosAdapter : ListAdapter<Images, PhotosAdapter.MarsPropertyViewHolder>(DiffCallback) {


    class MarsPropertyViewHolder(private var binding: GridItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(images: Images) {
            binding.image = images

            // forces the data binding to execute immediately
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Images]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Images>() {
        override fun areItemsTheSame(oldItem: Images, newItem: Images): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Images, newItem: Images): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }
}
