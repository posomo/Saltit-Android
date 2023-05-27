package com.posomo.saltit.login.onboarding

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.SeekBar
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.posomo.saltit.common_ui.R.color.saltit_blue_text
import com.posomo.saltit.common_ui.R.color.white
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.R
import com.posomo.saltit.login.databinding.FragmentOnboardingSecondBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class OnboardingSecondFragment : BaseFragment<FragmentOnboardingSecondBinding>(R.layout.fragment_onboarding_second) {

	private val viewModel by activityViewModels<OnboardingViewModel>()

	override fun initView() {
		(activity as ActivityUtil).changeStatusBarColor(getColor(white))

		initUI()
		subscribeUI()
	}

	private fun initOnboardingSecondHeadText() {
		val textData = getString(R.string.onboarding2_question_text_head)
		val builder = SpannableStringBuilder(textData)
		val colorBlueSpan = ForegroundColorSpan(getColor(saltit_blue_text))
		builder.setSpan(colorBlueSpan, 0, textData.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

		val boldSpan = StyleSpan(Typeface.BOLD)
		builder.setSpan(boldSpan, 0, textData.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

		binding.onboarding2QuestionTextHead.text = builder
	}

	private fun initUI() {
		initOnboardingSecondHeadText()

		binding.onboardingSecondBtn.setOnClickListener {
			val price = binding.onboarding2UserLunchPriceSeekbar.progress * 1000
			viewModel.storeIdealAvgLunchPrice(price)
		}

		val maxProgress = viewModel.currentAvgLunchPrice.value
		binding.onboarding2UserLunchPrice.text = getString(R.string.onboarding_price, maxProgress)
		binding.onboarding2UserLunchPriceSeekbar.progress = maxProgress

		binding.onboarding2UserLunchPriceSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

			override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

				if (progress > maxProgress) {
					seekBar.progress = maxProgress
					return
				}

				binding.onboarding2UserLunchPrice.text = getString(R.string.onboarding_price, seekBar.progress)
			}

			override fun onStartTrackingTouch(seekBar: SeekBar) {}
			override fun onStopTrackingTouch(seekBar: SeekBar) {}
		})
	}

	private fun subscribeUI() {
        launchOnLifecycleStarted {
            viewModel.storeIdealAvgLunchPriceSuccessFlow.collectLatest { isSuccess ->
                if (!isSuccess) return@collectLatest

                findNavController().navigate(R.id.action_onboardingSecondFragment_to_onboardingThirdFragment)
            }
        }
	}
}