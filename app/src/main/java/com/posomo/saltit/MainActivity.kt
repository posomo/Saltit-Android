package com.posomo.saltit

import com.posomo.saltit.domain.util.PreferenceUtil
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 하위 모듈들이 접근할 수 없는 코드를 [ActivityUtil]를 통해 하위 모듈에 제공합니다.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ActivityUtil {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    companion object {
        private lateinit var prefs: PreferenceUtil
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        // Splash 화면을 유지하고 싶을 경우 주석 해제
//        splashScreen.setKeepOnScreenCondition { true }

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        prefs = PreferenceUtil(applicationContext)
        supportActionBar?.hide()
        initView()

    }

    private fun initView() = with(binding) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.saltit_nav_host_fragment) as NavHostFragment
        
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(saltitBottomNav, navController)
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

    /** [PreferenceUtil] */
    override fun getUserCurrentAvgLunchPriceInLocal(price: Int): Int{
        return MainActivity.prefs.getUserCurrentAvgLunchPrice(price)
    }

    override fun setUserCurrentAvgLunchPriceInLocal(price: Int) {
        MainActivity.prefs.setUserCurrentAvgLunchPrice(price)
    }

    override fun getUserIdealAvgLunchPriceInLocal(price: Int): Int{
        return MainActivity.prefs.getUserIdealAvgLunchPrice(price)
    }

    override fun setUserIdealAvgLunchPriceInLocal(price: Int) {
        MainActivity.prefs.setUserIdealAvgLunchPrice(price)
    }

    /** [ActivityUtil] */
    override fun hideBottomNavigationView() {
        binding.saltitBottomNav.visibility = View.GONE
    }

    override fun navigateToHomeFragment() {
        binding.saltitBottomNav.visibility = View.VISIBLE
        navController.navigate(R.id.action_login_to_home)
    }

    override fun navigateToLoginFragment() {
        binding.saltitBottomNav.visibility = View.GONE
        navController.navigate(R.id.action_home_to_login)
    }

    override fun navigateToPlaceInfoFragment(id: Int) {
        binding.saltitBottomNav.visibility = View.GONE
        navController.navigate(R.id.action_home_to_place_info, bundleOf("restaurantId" to id))
    }

    override fun changeStatusBarColor(color: Int) {
        window.statusBarColor = color
    }
}