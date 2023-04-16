package com.posomo.saltit.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.posomo.saltit.model.search.SearchCardItem
import com.posomo.saltit.search.databinding.ListSearchCardItemBinding

class SearchCardListAdapter :
    ListAdapter<SearchCardItem, RecyclerView.ViewHolder>(SearchCardDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(ListSearchCardItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).onBindView(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    private inner class ItemViewHolder(private val binding: ListSearchCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                //TODO : 식당 세부 화면 이동 네비게이션
                Log.d("CardAdapter", "Click")
            }
        }

        fun onBindView(item: SearchCardItem) {
            binding.item = item
        }
    }

    private class SearchCardDiffUtil : DiffUtil.ItemCallback<SearchCardItem>() {
        override fun areItemsTheSame(oldItem: SearchCardItem, newItem: SearchCardItem): Boolean {
            return oldItem.id == newItem.id && oldItem.order == newItem.order && oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SearchCardItem, newItem: SearchCardItem): Boolean {
            return oldItem == newItem
        }

    }
}