package com.amanda.medicare.ui.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amanda.medicare.databinding.ActivityOnboardFirstBinding

class OnboardFirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSkipOb1.setOnClickListener(){
            val intent = Intent(this, OnboardThirdActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvNextOb1.setOnClickListener(){
            val intent = Intent(this, OnboardSecondActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}