package com.posomo.saltit.home

import androidx.core.content.ContextCompat
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.recyclerview.CommonAdapter
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.home.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

	private lateinit var commonAdapter: CommonAdapter

	override fun initView() {

		bind {

		}

		// StatusBar 색상 변경
		(activity as ActivityUtil).changeStatusBarColor(
			ContextCompat.getColor(
				requireContext(),
				com.posomo.saltit.common_ui.R.color.saltit_blue_background1
			)
		)
	}
}