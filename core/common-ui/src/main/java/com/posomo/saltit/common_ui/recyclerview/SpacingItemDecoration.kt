package com.posomo.saltit.common_ui.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
	private val orientation: String = "VERTICAL",
	private val spacing: Int
) : RecyclerView.ItemDecoration() {

	override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
		super.getItemOffsets(outRect, view, parent, state)

		if (orientation == "HORIZONTAL") {
			outRect.right = spacing
		} else {
			outRect.bottom = spacing
		}
	}
}