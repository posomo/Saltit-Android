package com.posomo.saltit.common_ui.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.posomo.saltit.common_ui.base.BaseFragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(list: ArrayList<BaseFragment<out ViewDataBinding>>, fm: FragmentActivity) : FragmentStateAdapter(fm) {
    private val fragmentList = list

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}
