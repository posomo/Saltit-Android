package com.posomo.saltit.home

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.posomo.saltit.common_ui.R.layout.item_saltit_pick_header
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.recyclerview.CommonAdapter
import com.posomo.saltit.common_ui.recyclerview.StickyHeaderItemDecoration
import com.posomo.saltit.common_ui.recyclerview.VHComponent
import com.posomo.saltit.common_ui.recyclerview.VHEventListener
import com.posomo.saltit.home.databinding.FragmentHomeBinding
import com.posomo.saltit.model.CommonItem
import com.posomo.saltit.model.ViewType

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), VHEventListener {

	private lateinit var commonAdapter: CommonAdapter

	override fun initView() {
		val testItems = getTestItems()

		commonAdapter = CommonAdapter(
			ViewType.SALTIT_PICK_HEADER,
			item_saltit_pick_header,
			this@HomeFragment
		)

		commonAdapter.submitList(testItems)

		binding.homeRv.apply {
			setHasFixedSize(true)
			layoutManager = LinearLayoutManager(requireContext())
			adapter = commonAdapter
			addItemDecoration(StickyHeaderItemDecoration(getSectionCallback()))
		}
	}

	private fun getTestItems(): List<CommonItem> {
		return mutableListOf<CommonItem>().apply {
			add(CommonItem(ViewType.SEARCH_HEADER))
			add(CommonItem(ViewType.SMALL_SPACER))
			add(CommonItem(ViewType.SALTIT_AMOUNT))
			add(CommonItem(ViewType.SPACER))
			add(CommonItem(ViewType.SALTIT_PICK_HEADER))
			add(CommonItem(ViewType.SMALL_SPACER))
			add(CommonItem((ViewType.WHITE_SPACER)))
			add(CommonItem(ViewType.SALTIT_PICK_Restaurant))
			add(CommonItem(ViewType.SALTIT_PICK_Restaurant))
			add(CommonItem(ViewType.SALTIT_PICK_Restaurant))
			add(CommonItem(ViewType.SALTIT_PICK_Restaurant))
		}.toList()
	}

	private fun getSectionCallback(): StickyHeaderItemDecoration.SectionCallback {
		return object : StickyHeaderItemDecoration.SectionCallback {
			override fun isHeader(position: Int): Boolean {
				return commonAdapter.isHeader(position)
			}

			override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
				return commonAdapter.getHeaderView(list, position)
			}
		}
	}

	override fun onClick(viewType: ViewType, component: VHComponent) {
		when (viewType) {
			ViewType.SEARCH_HEADER -> {
				when (component) {
					VHComponent.SearchHeaderComponent.AREA -> {
						Log.d("Check@@@", "여기는 지역 체크")
					}
					else -> {}
				}
			}
			ViewType.SALTIT_PICK_Restaurant -> {
				when (component) {
					VHComponent.SaltitPickStoreComponent.FILTER -> {
						Log.d("Check@@@", "Test1")
					}
					VHComponent.SaltitPickStoreComponent.REFRESH -> {
						Log.d("Check@@@", "Test2")
					}
					else -> {}
				}
			}
			else -> {}
		}
	}

}