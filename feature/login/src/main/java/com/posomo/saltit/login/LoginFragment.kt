package com.posomo.saltit.login

import com.posomo.saltit.common_ui.base.BaseFragment
import com.posomo.saltit.common_ui.util.ActivityUtil
import com.posomo.saltit.login.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun initView() {
        (activity as ActivityUtil).hideBottomNavigationView()

        binding.loginBtn.setOnClickListener {
            (activity as ActivityUtil).navigateToHomeFragment()
        }
    }
}