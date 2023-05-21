package com.posomo.saltit.search

import android.Manifest
import android.view.Gravity
import androidx.annotation.UiThread
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapOptions
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search), OnMapReadyCallback {

    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource

    private val adapter: SearchCardListAdapter by lazy { SearchCardListAdapter() }
    private val mapFragment by lazy {
        childFragmentManager.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance(NaverMapOptions().locationButtonEnabled(false)).also {
                childFragmentManager.beginTransaction().add(R.id.map, it).commit()
            }
    }

    override fun initView() {
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
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
        naverMap.locationOverlay.isVisible = true
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.uiSettings.isLocationButtonEnabled = false
        naverMap.uiSettings.logoGravity = (Gravity.END or Gravity.BOTTOM)
        val logoMargin = requireContext().resources.displayMetrics.density.toInt() * 12
        naverMap.uiSettings.setLogoMargin(0, 0, logoMargin, binding.viewPager.height + logoMargin)
        binding.locationButton.map = naverMap

        ActivityCompat.requestPermissions(requireActivity(), PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)

        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.getAroundRestaurants()
        viewModel.aroundRestaurants.value.forEach {
            val marker = Marker()
            marker.position = LatLng(it.lat, it.log)
            marker.map = naverMap
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
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        private val PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    }
}