package com.example.asteroidradar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidradar.databinding.AsteroidItemBinding

class AsteroidAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Asteroids, AsteroidAdapter.AsteroidViewHolder>(DiffCallback) {
    class AsteroidViewHolder(private var binding: AsteroidItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroids) {
            binding.asteroid = asteroid
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {

        return AsteroidViewHolder(
            AsteroidItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(asteroid)
        }
        holder.bind(asteroid)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroids>() {
        override fun areItemsTheSame(oldItem: Asteroids, newItem: Asteroids): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroids, newItem: Asteroids): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (Asteroids) -> Unit) {
        fun onClick(asteroid: Asteroids) = clickListener(asteroid)
    }

}
