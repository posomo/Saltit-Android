package com.posomo.saltit.login

import android.content.Context
import androidx.core.content.ContextCompat
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.databinding.FragmentOnboardingChildThirdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentOnboardingChildThirdBinding>(R.layout.fragment_onboarding_child_third) {

    override fun initView() {
        (activity as ActivityUtil).hideBottomNavigationView()

        (activity as ActivityUtil).changeStatusBarColor(
            ContextCompat.getColor(
                requireContext(),
                com.posomo.saltit.common_ui.R.color.white
            )
        )

        val userCurrentAvgLunchPrice = (activity as ActivityUtil).getUserCurrentAvgLunchPriceInLocal(0)
        val userIdealAvgLunchPrice = (activity as ActivityUtil).getUserIdealAvgLunchPriceInLocal(0)
        val userSavingLunchPrice = ((userCurrentAvgLunchPrice - userIdealAvgLunchPrice)*30).toString()

        if(userSavingLunchPrice.length == 5 ) {
            binding.onboarding3MonthlySavingMoney.text = userSavingLunchPrice.substring(0,2) + ",000"
        }
        else if(userSavingLunchPrice.length == 6) {
            binding.onboarding3MonthlySavingMoney.text = userSavingLunchPrice.substring(0,3) + ",000"
        }
        else {
            binding.onboarding3MonthlySavingMoney.text = "15,000"
        }

        binding.loginBtn.setOnClickListener {
            (activity as ActivityUtil).navigateToHomeFragment()
            onBoardingFinished()
        }
    }

    private fun onBoardingFinished() {
        val prefs = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("finished", true).apply()
    }

}