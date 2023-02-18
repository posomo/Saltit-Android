package com.posomo.saltit.common_ui.recyclerview

import com.posomo.saltit.common_ui.databinding.ItemSearchHeaderBinding
import com.posomo.saltit.model.CommonItem

class SearchHeaderVH(
    private val binding: ItemSearchHeaderBinding,
    private val vhEventListener: VHEventListener
) : CommonVH(binding) {

    init {
        binding.searchHeaderEdittext.setOnClickListener {

        }
    }

    override fun bind(item: CommonItem) {
        binding.searchHeaderRegionText.setOnClickListener {
            vhEventListener.onClick(item.viewType, VHComponent.SearchHeaderCp.AREA)
        }
    }
}