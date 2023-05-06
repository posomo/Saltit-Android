package com.posomo.saltit.home

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.recyclerview.SpacingItemDecoration
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

	private val viewModel by viewModels<HomeViewModel>()
	private val homeAdapter by lazy { HomeAdapter() }

	override fun initView() {

		bind {
			adapter = homeAdapter
			itemDecoration = SpacingItemDecoration(spacing = 40)
		}

		subscribeUI()

		// StatusBar 색상 변경
		(activity as ActivityUtil).changeStatusBarColor(
			ContextCompat.getColor(
				requireContext(),
				com.posomo.saltit.common_ui.R.color.saltit_blue_background1
			)
		)
	}

	private fun subscribeUI() {
		viewModel.restaurantSummaries.observe(viewLifecycleOwner) {
			homeAdapter.submitList(it) {
				binding.homeRv.requestLayout()
			}
		}
	}
}