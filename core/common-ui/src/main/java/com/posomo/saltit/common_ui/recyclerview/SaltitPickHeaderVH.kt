package com.posomo.saltit.common_ui.recyclerview

import com.posomo.saltit.common_ui.databinding.ItemSaltitPickHeaderBinding
import com.posomo.saltit.model.CommonItem

class SaltitPickHeaderVH(
    private val binding: ItemSaltitPickHeaderBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        binding.button1.setOnClickListener {
        }

        binding.button2.setOnClickListener {
        }
    }

}