package com.posomo.saltit.login

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.posomo.saltit.common_ui.R.color.saltit_blue_background
import com.posomo.saltit.common_ui.R.color.white
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

	override fun initView() {
		(activity as ActivityUtil).hideBottomNavigationView()

		(activity as ActivityUtil).changeStatusBarColor(
			ContextCompat.getColor(
				requireContext(),
				saltit_blue_background
			)
		)

		CoroutineScope(Dispatchers.Main).launch {
			delay(3000)

			if (isOnBoardingFinished()) {
				(activity as ActivityUtil).navigateToHomeFragment()
			} else {
				findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
			}

			(activity as ActivityUtil).changeStatusBarColor(
				ContextCompat.getColor(
					requireContext(),
					white
				)
			)
		}
	}

	private fun isOnBoardingFinished(): Boolean {
		val prefs = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
		return prefs.getBoolean("finished", false)
	}
}