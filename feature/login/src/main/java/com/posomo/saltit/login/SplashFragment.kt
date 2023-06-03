package com.posomo.saltit.login

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.posomo.saltit.common_ui.R.color.saltit_blue_background
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

	private val viewModel by viewModels<SplashViewModel>()

	override fun initView() {
		(activity as ActivityUtil).hideBottomNavigationView()

		(activity as ActivityUtil).changeStatusBarColor(
			ContextCompat.getColor(
				requireContext(),
				saltit_blue_background
			)
		)

		subscribeUI()
	}

	private fun subscribeUI() {
		launchOnLifecycleStarted {
			viewModel.onboardingFinishStatus.collectLatest {isFinished ->
				if (isFinished == null) return@collectLatest

				if(isFinished) {
					(activity as ActivityUtil).navigateToHomeFragment()
				} else {
					findNavController().navigate(R.id.action_splashFragment_to_onboardingFirstFragment)
				}
			}
		}
	}
}