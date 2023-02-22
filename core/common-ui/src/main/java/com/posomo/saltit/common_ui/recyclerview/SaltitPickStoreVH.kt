package com.posomo.saltit.common_ui.recyclerview

import com.posomo.saltit.common_ui.databinding.ItemSaltitPickStoreBinding
import com.posomo.saltit.model.CommonItem

class SaltitPickStoreVH(
    private val binding: ItemSaltitPickStoreBinding,
    private val vhEventListener: VHEventListener
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        binding.setClickListener {

        }

        binding.text1.setOnClickListener {
            vhEventListener.onClick(item.viewType, VHComponent.SaltitPickStoreComponent.FILTER)
        }

        binding.text2.setOnClickListener {
            vhEventListener.onClick(item.viewType, VHComponent.SaltitPickStoreComponent.REFRESH)
        }

    }
}