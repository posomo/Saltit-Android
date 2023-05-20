package com.posomo.saltit.login

import dagger.hilt.android.AndroidEntryPoint
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import androidx.core.content.ContextCompat
import com.posomo.saltit.common_ui.recyclerview.ViewPagerAdapter
import com.posomo.saltit.login.databinding.FragmentOnboardingBinding
import com.posomo.saltit.login.onboarding.*

@AndroidEntryPoint
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>(R.layout.fragment_onboarding) {

    override fun initView() {
        (activity as ActivityUtil).hideBottomNavigationView()

        (activity as ActivityUtil).changeStatusBarColor(
            ContextCompat.getColor(
                requireContext(),
                com.posomo.saltit.common_ui.R.color.white
            )
        )
        //1
        setupViewPager()
    }
     fun setupViewPager() {
        val fragmentList = arrayListOf(
            OnboardingChildFirstFragment.newInstance(),
            OnboardingChildSecondFragment.newInstance(),
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity(),
        )

        binding.viewPager.adapter = adapter
        //2
        binding.viewPager.isUserInputEnabled = false
    }

}