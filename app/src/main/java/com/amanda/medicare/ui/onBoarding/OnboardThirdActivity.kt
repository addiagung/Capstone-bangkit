package com.amanda.medicare.ui.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amanda.medicare.databinding.ActivityOnboardThirdBinding
import com.amanda.medicare.ui.main.MainActivity

class OnboardThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvStartOb3.setOnClickListener(){
            moveToMainActivity()
        }
    }

    private fun moveToMainActivity() {
        setOnboardingCompleted()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        // Set onboarding status to completed


        finish()
    }

    private fun setOnboardingCompleted() {
        val sharedPreferences = getSharedPreferences("onboarding", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("onboarding_complete", true)
        editor.apply()
    }
}