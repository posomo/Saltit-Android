package com.posomo.saltit.common_ui.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.posomo.saltit.common_ui.base.BaseFragment

class ViewPagerAdapter(list: ArrayList<BaseFragment<out ViewDataBinding>>, fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    private val fragmentList = list

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}
