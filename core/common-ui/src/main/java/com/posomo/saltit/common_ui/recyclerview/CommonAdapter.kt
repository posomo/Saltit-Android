package com.posomo.saltit.common_ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.posomo.saltit.model.CommonItem
import com.posomo.saltit.model.ViewType

class CommonAdapter(
	private val headerViewType: ViewType? = null,
	private val headerLayoutRes: Int? = null,
	private val vhEventListener: VHEventListener
) : RecyclerView.Adapter<CommonVH>() {

	private val items = mutableListOf<CommonItem>()

	fun submitList(newItems: List<CommonItem>) {
		val prevSize = items.size
		items.addAll(newItems)
		notifyItemRangeChanged(prevSize, newItems.size)
	}

	fun modifyItem(position: Int, item: CommonItem) {
		items[position] = item
		notifyItemChanged(position)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
		return CommonVHFactory.createViewHolder(parent, viewType, vhEventListener)
	}

	override fun onBindViewHolder(holder: CommonVH, position: Int) {
		holder.bind(items[position])
	}

	override fun getItemCount(): Int = items.size

	override fun getItemViewType(position: Int): Int =
		ViewType.valueOf(items[position].viewType.toString()).ordinal


	/* StickyHeader를 위한 함수들 */
	fun isHeader(position: Int) = items[position].viewType == headerViewType

	fun getHeaderView(list: RecyclerView, position: Int): View? {
		if (headerLayoutRes == null || headerViewType == null) return null

		val lastIndex =
			if (position < items.size)
				position else items.size - 1
		for (index in lastIndex downTo 0) {
			val model = items[index]
			if (model.viewType == headerViewType) {
				return LayoutInflater.from(list.context)
					.inflate(headerLayoutRes, list, false)
			}
		}
		return null
	}
}