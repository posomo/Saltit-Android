package com.posomo.saltit.place_info

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.place_info.databinding.FragmentPlaceInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlaceInfoFragment : BaseFragment<FragmentPlaceInfoBinding>(R.layout.fragment_place_info) {

    private val viewModel by viewModels<PlaceInfoViewModel>()
    private val dataList = mutableListOf<PlaceInfoListItem>()
    private val mAdapter: PlaceInfoAdapter = PlaceInfoAdapter()

    override fun initView() {
        viewModel.initDetailInfo(arguments?.getInt("restaurantId") ?: return)

        lifecycleScope.launch {
            viewModel.restaurantDetail.flowWithLifecycle(lifecycle).collect { restaurantDetail ->
                restaurantDetail?.let { item ->
                    dataList.apply {
                        add(PlaceInfoListItem.MainImage(item.titleImageUrl))
                        add(PlaceInfoListItem.Header(item))
                        add(PlaceInfoListItem.Tab)
                        add(PlaceInfoListItem.MenuHeader("전체메뉴"))
                        add(PlaceInfoListItem.MenuCategory("메인메뉴"))
                        addAll(item.mainMenuList.map { PlaceInfoListItem.MenuItem(it) })
                        add(PlaceInfoListItem.MenuFooter)
                        add(PlaceInfoListItem.MenuCategory("사이드 메뉴"))
                        addAll(item.sideMenuList.map { PlaceInfoListItem.MenuItem(it) })
                        add(PlaceInfoListItem.MenuFooter)
                        add(PlaceInfoListItem.DecorGray)
                        add(PlaceInfoListItem.Review(item.diningcodeUrl))
                        add(PlaceInfoListItem.Footer)
                    }
                    mAdapter.submitList(dataList)
                }
            }
        }

        bind {
            adapter = mAdapter
            vm = viewModel
        }
    }
}