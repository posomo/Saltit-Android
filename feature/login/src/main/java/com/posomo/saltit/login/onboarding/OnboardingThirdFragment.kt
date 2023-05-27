package com.posomo.saltit.login.onboarding

import androidx.fragment.app.activityViewModels
import com.posomo.saltit.common_ui.R.color.white
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.R
import com.posomo.saltit.login.databinding.FragmentOnboardingThirdBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class OnboardingThirdFragment : BaseFragment<FragmentOnboardingThirdBinding>(R.layout.fragment_onboarding_third) {

    private val viewModel by activityViewModels<OnboardingViewModel>()

    override fun initView() {
        (activity as ActivityUtil).changeStatusBarColor(getColor(white))

        initUI()
        subscribeUI()
    }

    private fun initUI() {
        var savingLunchPrice = (viewModel.currentAvgLunchPrice.value - viewModel.idealAvgLunchPrice.value) * 30
        if (savingLunchPrice == 0) savingLunchPrice = 15
        binding.onboarding3MonthlySavingMoney.text = getString(R.string.onboarding_price, savingLunchPrice)

        binding.onboardingThirdBtn.setOnClickListener {
            viewModel.finishOnboarding()
        }
    }

    private fun subscribeUI() {
        launchOnLifecycleStarted {
            viewModel.storeOnboardingSuccessFlow.collectLatest { isSuccess ->
                if (!isSuccess) return@collectLatest

                (activity as ActivityUtil).navigateToHomeFragment()
            }
        }
    }

}