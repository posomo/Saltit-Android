package com.posomo.saltit.common_ui.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.posomo.saltit.model.CommonItem

abstract class CommonVH(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: CommonItem)
}