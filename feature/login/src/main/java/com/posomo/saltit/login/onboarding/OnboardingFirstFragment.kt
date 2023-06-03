package com.posomo.saltit.login.onboarding

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.SeekBar
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.posomo.saltit.common_ui.R.color.saltit_sub_orange_Text
import com.posomo.saltit.common_ui.R.color.white
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.R
import com.posomo.saltit.login.databinding.FragmentOnboardingFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class OnboardingFirstFragment : BaseFragment<FragmentOnboardingFirstBinding>(R.layout.fragment_onboarding_first) {

	private val viewModel by activityViewModels<OnboardingViewModel>()

	override fun initView() {

		(activity as ActivityUtil).changeStatusBarColor(getColor(white))

		initUI()
		subscribeUI()
	}

	private fun initOnboardingText() {
		val textHeadData: String = getString(R.string.onboarding_question_text_head)
		val builder = SpannableStringBuilder(textHeadData)

		val colorBlueSpan = ForegroundColorSpan(getColor(saltit_sub_orange_Text))
		builder.setSpan(colorBlueSpan, 0, textHeadData.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

		val boldSpan = StyleSpan(Typeface.BOLD)
		builder.setSpan(boldSpan, 0, textHeadData.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

		binding.onboardingQuestionTextHead.text = builder
	}

	private fun initUI() {


		initOnboardingText()

		binding.onboardingFirstBtn.setOnClickListener {
			val price = binding.onboardingUserLunchPriceSeekbar.progress * 1000
			viewModel.storeCurrentAvgLunchPrice(price)
		}

		binding.onboardingUserLunchPriceSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
			override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
				binding.onboardingUserLunchPrice.text = getString(R.string.onboarding_price, progress)
			}

			override fun onStartTrackingTouch(seekBar: SeekBar?) {}
			override fun onStopTrackingTouch(seekBar: SeekBar?) {}
		})
	}

	private fun subscribeUI() {

		launchOnLifecycleStarted {
			viewModel.storeCurrentAvgLunchPriceSuccessFlow.collectLatest { isSuccess ->
				if (!isSuccess) return@collectLatest

				findNavController().navigate(R.id.action_onboardingFirstFragment_to_onboardingSecondFragment)
			}
		}
	}
}