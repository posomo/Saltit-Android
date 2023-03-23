package com.posomo.saltit.login

import androidx.navigation.fragment.findNavController
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

	override fun initView() {
		(activity as ActivityUtil).hideBottomNavigationView()

		CoroutineScope(Dispatchers.Main).launch {
			delay(3000)

			findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
		}
	}
}