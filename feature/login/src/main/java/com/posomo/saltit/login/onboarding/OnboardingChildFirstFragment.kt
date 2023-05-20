package com.posomo.saltit.login.onboarding

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.R
import com.posomo.saltit.login.databinding.FragmentOnboardingChildFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingChildFirstFragment : BaseFragment<FragmentOnboardingChildFirstBinding>(R.layout.fragment_onboarding_child_first) {

    override fun initView() {
        (activity as ActivityUtil).hideBottomNavigationView()

        (activity as ActivityUtil).changeStatusBarColor(
            ContextCompat.getColor(
                requireContext(),
                com.posomo.saltit.common_ui.R.color.white
            )
        )

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        val textResourceId = R.string.onboarding_question_text_head
        val textData: String = getString(textResourceId)
        val builder = SpannableStringBuilder(textData)
        var userOldLunchPrice: Int = 0

        val colorBlueSpan = ForegroundColorSpan(Color.rgb(221, 101, 15))
        builder.setSpan(colorBlueSpan, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val boldSpan = StyleSpan(Typeface.BOLD)
        builder.setSpan(boldSpan,0,6,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.onboardingQuestionTextHead.text = builder

        binding.txtNext.setOnClickListener {
            userOldLunchPrice /= 1000
            userOldLunchPrice *= 1000

            (activity as ActivityUtil).setUserCurrentAvgLunchPriceInLocal(userOldLunchPrice)

            viewPager?.currentItem = 1
        }

        binding.onboardingUserLunchPriceSeekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val seekBarLunchPrice = progress.toString()
                userOldLunchPrice = progress

                if(seekBarLunchPrice.length ==4 ) {
                    binding.onboardingUserLunchPrice.text = seekBarLunchPrice.substring(0,1) + ",000"
                }
                else {
                    binding.onboardingUserLunchPrice.text = seekBarLunchPrice.substring(0,2) + ",000"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }


    companion object {
        fun newInstance() = OnboardingChildFirstFragment()
    }
}