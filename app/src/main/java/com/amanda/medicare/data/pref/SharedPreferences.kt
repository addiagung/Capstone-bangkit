package com.amanda.medicare.data.pref

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences (context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("onboarding_pref", Context.MODE_PRIVATE)

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_first_time_launch", isFirstTime)
        editor.apply()
    }

    fun isFirstTimeLaunch(): Boolean {
        return sharedPreferences.getBoolean("is_first_time_launch", true)
    }
}