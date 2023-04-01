package com.posomo.saltit.common_ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.posomo.saltit.common_ui.R
import com.posomo.saltit.model.ViewType

object CommonVHFactory {
	fun createViewHolder(parent: ViewGroup, viewType: Int, vhEventListener: VHEventListener): CommonVH {
		return when (viewType) {
			ViewType.SEARCH_HEADER.ordinal -> SearchHeaderVH(getViewDataBinding(parent, R.layout.item_search_header), vhEventListener)
			ViewType.SALTIT_AMOUNT.ordinal -> SaltitCashBoxVH(getViewDataBinding(parent, R.layout.item_saltit_cash_box))
			ViewType.SALTIT_PICK_HEADER.ordinal -> SaltitPickHeaderVH(
				getViewDataBinding(parent, R.layout.item_saltit_pick_header))
			ViewType.SALTIT_PICK_Restaurant.ordinal -> SaltitPickRestaurantVH(getViewDataBinding(parent, R.layout.item_saltit_pick_restaurant))
			ViewType.SMALL_SPACER.ordinal -> SmallSpacerVH(getViewDataBinding(parent, R.layout.item_small_spacer))
			ViewType.WHITE_SPACER.ordinal -> WhiteSpacerVH(getViewDataBinding(parent,R.layout.item_white_spacer))
			else -> SpacerVH(getViewDataBinding(parent, R.layout.item_spacer))
		}
	}

	private fun <T : ViewDataBinding> getViewDataBinding(parent: ViewGroup, layoutRes: Int): T {
		return DataBindingUtil.inflate(
			LayoutInflater.from(parent.context),
			layoutRes,
			parent,
			false
		)
	}
}
