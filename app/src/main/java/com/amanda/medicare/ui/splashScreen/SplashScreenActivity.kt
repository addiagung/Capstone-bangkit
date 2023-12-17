package com.amanda.medicare.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.amanda.medicare.data.pref.SharedPreferences
import com.amanda.medicare.databinding.ActivitySplashScreenBinding
import com.amanda.medicare.ui.main.MainActivity
import com.amanda.medicare.ui.onBoarding.OnboardFirstActivity


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesHelper: SharedPreferences

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        sharedPreferencesHelper = SharedPreferences(this)

        Handler(Looper.getMainLooper()).postDelayed({
            navigateToNextActivity()
        }, SPLASH_SCREEN_DELAY)
    }

    private fun navigateToNextActivity() {
        if (sharedPreferencesHelper.isFirstTimeLaunch()) {
            startActivity(Intent(this, OnboardFirstActivity::class.java))
            sharedPreferencesHelper.setFirstTimeLaunch(false)
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY = 3000L // Waktu tundaan dalam milidetik (contoh: 2000L untuk 2 detik)
    }
}