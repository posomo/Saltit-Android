package com.posomo.saltit.place_info

import androidx.fragment.app.viewModels
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.place_info.databinding.FragmentPlaceInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceInfoFragment : BaseFragment<FragmentPlaceInfoBinding>(R.layout.fragment_place_info) {

    private val viewModel by viewModels<PlaceInfoViewModel>()

    override fun initView() {

    }

}