package com.posomo.saltit.domain.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context){
    private val prefs: SharedPreferences =
        context.getSharedPreferences("userLunchPriceOriginal", Context.MODE_PRIVATE)

    fun getUserCurrentAvgLunchPrice(price: Int) : Int {
        return prefs.getInt("userCurrentAvgLunchPrice", price)
    }

    fun setUserCurrentAvgLunchPrice(price: Int) {
        prefs.edit().putInt("userCurrentAvgLunchPrice", price).apply()
    }

    fun getUserIdealAvgLunchPrice(price: Int) : Int {
        return prefs.getInt("userIdealAvgLunchPrice", price)
    }

    fun setUserIdealAvgLunchPrice(price: Int) {
        prefs.edit().putInt("userIdealAvgLunchPrice", price).apply()
    }
}