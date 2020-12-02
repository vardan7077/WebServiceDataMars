package com.example.marswebservicecontact.photoGridAdapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marswebservicecontact.databinding.GridItemViewBinding
import com.example.marswebservicecontact.network.MarsClass

class PhotoGridAdapter(private val clickListener: onClickListener): ListAdapter<MarsClass, PhotoGridAdapter.ItemViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<MarsClass>() {
        override fun areItemsTheSame(oldItem: MarsClass, newItem: MarsClass): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsClass, newItem: MarsClass): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ItemViewHolder(private var binding: GridItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsClass) {
            binding.property = marsProperty
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridItemViewBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.ItemViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener.onClick(marsProperty)

        }
        holder.bind(marsProperty)
    }

    class onClickListener(val clickListener: (marsProperty:MarsClass) ->Unit){
        fun onClick(marsProperty: MarsClass) = clickListener(marsProperty)
    }
}