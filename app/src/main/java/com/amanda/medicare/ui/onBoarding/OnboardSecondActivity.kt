package com.amanda.medicare.ui.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amanda.medicare.databinding.ActivityOnboardSecondBinding

class OnboardSecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSkipOb2.setOnClickListener(){
            val intent = Intent(this, OnboardThirdActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvNextOb2.setOnClickListener(){
            val intent = Intent(this, OnboardThirdActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}