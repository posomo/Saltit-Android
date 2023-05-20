package com.posomo.saltit.login.onboarding

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.R
import com.posomo.saltit.login.databinding.FragmentOnboardingChildSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingChildSecondFragment : BaseFragment<FragmentOnboardingChildSecondBinding>(R.layout.fragment_onboarding_child_second) {

    override fun initView() {
        (activity as ActivityUtil).hideBottomNavigationView()

        (activity as ActivityUtil).changeStatusBarColor(
            ContextCompat.getColor(
                requireContext(),
                com.posomo.saltit.common_ui.R.color.white
            )
        )

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val textResourceId = R.string.onboarding2_question_text_head
        val textData: String = getString(textResourceId)
        val builder = SpannableStringBuilder(textData)
        var userTargetLunchPrice: Int = 0
        val userOldLunchPrice = (activity as ActivityUtil).getUserCurrentAvgLunchPriceInLocal(20000)
        val colorBlueSpan = ForegroundColorSpan(Color.rgb(0, 186, 211))
        builder.setSpan(colorBlueSpan, 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val boldSpan = StyleSpan(Typeface.BOLD)
        builder.setSpan(boldSpan,0,8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.onboarding2QuestionTextHead.text = builder

        binding.txtNext.setOnClickListener {

            userTargetLunchPrice /= 1000
            userTargetLunchPrice *= 1000

            (activity as ActivityUtil).setUserIdealAvgLunchPriceInLocal(userTargetLunchPrice)

            //viewPager?.currentItem = 2
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }

        binding.onboarding2UserLunchPriceSeekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            var progressChanged = false

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val maxProgress = userOldLunchPrice // 일정 수준 이상의 값으로 설정하려는 기준 값

                if (progress > maxProgress) {
                    seekBar.progress = maxProgress
                    progressChanged = true
                } else {
                    progressChanged = false
                }

                val seekBarLunchPrice = seekBar.progress.toString()
                if (seekBarLunchPrice.length == 4) {
                    binding.onboarding2UserLunchPrice.text = seekBarLunchPrice.substring(0, 1) + ",000"
                } else {
                    binding.onboarding2UserLunchPrice.text = seekBarLunchPrice.substring(0, 2) + ",000"
                }
                userTargetLunchPrice = seekBar.progress

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (progressChanged) {
                    val maxProgress = userOldLunchPrice // 일정 수준 이상의 값으로 설정하려는 기준 값
                    seekBar.progress = maxProgress
                }
                userTargetLunchPrice = seekBar.progress
            }
        })

    }

    companion object {
        fun newInstance() = OnboardingChildSecondFragment()
    }

}