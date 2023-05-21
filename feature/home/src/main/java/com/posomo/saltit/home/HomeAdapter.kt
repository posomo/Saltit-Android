package com.posomo.saltit.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.posomo.saltit.home.databinding.ItemSaltitPickRestaurantBinding
import com.posomo.saltit.model.domain.RestaurantSummary

class HomeAdapter(
	private val onItemClick: (Int) -> Unit
) : ListAdapter<RestaurantSummary, HomeAdapter.ViewHolder>(RestaurantSummaryCallback) {

	inner class ViewHolder(private val binding: ItemSaltitPickRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: RestaurantSummary) {
			binding.item = item
			binding.setClickListener {
				onItemClick(item.restaurantId)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding = ItemSaltitPickRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(currentList[position])
	}

	object RestaurantSummaryCallback : DiffUtil.ItemCallback<RestaurantSummary>() {
		override fun areItemsTheSame(oldItem: RestaurantSummary, newItem: RestaurantSummary): Boolean {
			return oldItem == newItem
		}

		override fun areContentsTheSame(oldItem: RestaurantSummary, newItem: RestaurantSummary): Boolean {
			return oldItem.restaurantName == newItem.restaurantName
		}
	}
}