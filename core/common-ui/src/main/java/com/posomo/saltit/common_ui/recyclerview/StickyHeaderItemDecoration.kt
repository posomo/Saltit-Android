package com.posomo.saltit.common_ui.recyclerview

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StickyHeaderItemDecoration(
	private val sectionCallback: SectionCallback
) : RecyclerView.ItemDecoration() {

	override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
		super.onDrawOver(c, parent, state)

		// 현재 맨 위에 있는 view를 얻는다. 현재 recyclerView에 보이는 첫 번째 뷰
		val topChild = parent.getChildAt(0) ?: return

		// 맨 위에 있는 view의 position을 얻는다
		val topChildPosition = parent.getChildAdapterPosition(topChild)

		// 현재 맨 위에 있는 position을 이용해서 itemView를 얻는다
		// 현재 맨 위에 있는 position이 헤더에 필요한 뷰인지 아닌지에 따라 다른 itemView를 넘겨준다.
		val currentHeader: View = sectionCallback.getHeaderLayoutView(parent, topChildPosition) ?: return

		// itemView를 Measure, Layout하는 과정을 거친다.
		fixLayoutSize(parent, currentHeader)

		val contactPoint = currentHeader.bottom
		val childInContact: View = (getChildInContact(parent, contactPoint) ?: return)

		val childAdapterPosition = parent.getChildAdapterPosition(childInContact)
		if (childAdapterPosition == -1) return

		// 해더가 2개일 경우 위로 밀어내기
		if (sectionCallback.isHeader(childAdapterPosition)) {
			moveHeader(c, currentHeader, childInContact)
			return
		}

		drawHeader(c, currentHeader)
	}

	private fun getChildInContact(parent: RecyclerView, contactPoint: Int): View? {
		var childInContact: View? = null
		for (i in 0 until parent.childCount) {
			val child = parent.getChildAt(i)
			if (child.bottom > contactPoint) {
				if (child.top <= contactPoint) {
					childInContact = child
					break
				}
			}
		}
		return childInContact
	}

	private fun moveHeader(c: Canvas, currentHeader: View, nextHeader: View) {
		c.save()
		c.translate(0f, nextHeader.top - currentHeader.height.toFloat())
		currentHeader.draw(c)
		c.restore()
	}

	private fun drawHeader(c: Canvas, header: View) {
		c.save()
		c.translate(0f, 0f)
		header.draw(c)
		c.restore()
	}

	// Mesaure -> 뷰 그룹과 뷰의 요소들의 크기를 결정, 뷰 그룹의 크기가 측정되면 자식 뷰들의 크기도 함께 측정
	// Layout -> measure 단계에서 측정한 사이즈를 이용해서 각 뷰 그룹은 자식 뷰의 위치를 결정하는 새로운 Top-down traversal 과정을 진행
	// Draw -> 실제로 뷰를 그리는 과정

	private fun fixLayoutSize(parent: ViewGroup, view: View) {

		// View.MeasureSpec.EXACTLY: match_parent와 같이 정확한 사이즈가 정해진 경우에 할당
		val widthSpec = View.MeasureSpec.makeMeasureSpec(
			parent.width,
			View.MeasureSpec.EXACTLY
		)

		// View.MeasureSpec.AT_MOST: 주어진 사이즈에서 원하는 크기를 가질 수 있다.
		val heightSpec = View.MeasureSpec.makeMeasureSpec(
			parent.height,
			View.MeasureSpec.AT_MOST
		)

		val childWidth: Int = ViewGroup.getChildMeasureSpec(
			widthSpec,
			parent.paddingLeft + parent.paddingRight,
			view.layoutParams.width
		)

		val childHeight: Int = ViewGroup.getChildMeasureSpec(
			heightSpec,
			parent.paddingTop + parent.paddingBottom,
			view.layoutParams.height
		)

		view.measure(childWidth, childHeight)
		view.layout(0, 0, view.measuredWidth, view.measuredHeight)
	}


	interface SectionCallback {
		fun isHeader(position: Int): Boolean
		fun getHeaderLayoutView(list: RecyclerView, position: Int): View?
	}
}