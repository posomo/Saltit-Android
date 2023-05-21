package com.posomo.saltit.common_ui.util

/**
 * Activity를 필요로 하는 기능을 명시하고 App 모듈에 있는 MainActivity에서 구현합니다.
 */
interface ActivityUtil {

    // 바텀 네비게이션 숨기는 기능
    fun hideBottomNavigationView()

    // 로그인에서 홈화면으로 이동
    fun navigateToHomeFragment()

    // 로그인에서 홈화면으로 이동
    fun navigateToLoginFragment()

    // 홈화면에서 가게 상세 화면으로 이동
    fun navigateToPlaceInfoFragment(id: Int)

    // StatusBar Color 변경
    fun changeStatusBarColor(color: Int)

    fun getUserCurrentAvgLunchPriceInLocal(price: Int): Int

    fun setUserCurrentAvgLunchPriceInLocal(price: Int)

    fun getUserIdealAvgLunchPriceInLocal(price: Int): Int

    fun setUserIdealAvgLunchPriceInLocal(price: Int)
}