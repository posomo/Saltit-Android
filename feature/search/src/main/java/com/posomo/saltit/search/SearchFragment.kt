package com.posomo.saltit.search

import android.view.Gravity
import androidx.annotation.UiThread
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.search.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search),
    OnMapReadyCallback {

    private val mapFragment by lazy {
        childFragmentManager.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.map, it).commit()
            }
    }

    private val locationSource by lazy {
        FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    private lateinit var naverMap: NaverMap
    private lateinit var uiSettings: UiSettings
    private val adapter: SearchCardListAdapter by lazy { SearchCardListAdapter() }

    override fun initView() {
        mapFragment.getMapAsync(this)
        with(binding) {
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 1
            viewPager.setPageTransformer { page, position ->
                page.translationX = position * -calculatePagerMargin()
            }
        }
    }

    private fun calculatePagerMargin(): Int {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels

        return screenWidth - pageMarginPx - pagerWidth
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        uiSettings = naverMap.uiSettings.apply {
            isCompassEnabled = false
            isLocationButtonEnabled = false
            isZoomControlEnabled = false
        }
        mapFragment.getMapAsync {
            binding.locationButton.map = naverMap
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) {
                naverMap.locationTrackingMode = LocationTrackingMode.None
            } else {
                naverMap.locationTrackingMode = LocationTrackingMode.Follow
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }

}