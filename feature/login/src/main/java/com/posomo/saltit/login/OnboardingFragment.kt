package com.posomo.saltit.login

import androidx.core.content.ContextCompat
import com.posomo.saltit.common_ui.R.color.white
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.recyclerview.ViewPagerAdapter
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.databinding.FragmentOnboardingBinding
import com.posomo.saltit.login.onboarding.OnboardingChildFirstFragment
import com.posomo.saltit.login.onboarding.OnboardingChildSecondFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>(R.layout.fragment_onboarding) {

	private val onboardingAdapter by lazy {
		ViewPagerAdapter(
			arrayListOf(
				OnboardingChildFirstFragment.newInstance(),
				OnboardingChildSecondFragment.newInstance(),
			),
			childFragmentManager,
			lifecycle
		)
	}

	override fun initView() {

		initUI()

		bind {
			adapter = onboardingAdapter
		}
	}

	private fun initUI() {
		(activity as ActivityUtil).hideBottomNavigationView()

		(activity as ActivityUtil).changeStatusBarColor(
			ContextCompat.getColor(
				requireContext(),
				white
			)
		)
	}
}