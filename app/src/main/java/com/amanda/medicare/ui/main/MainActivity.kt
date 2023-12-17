package com.amanda.medicare.ui.main

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.amanda.medicare.data.utils.getImageUri
import com.amanda.medicare.databinding.ActivityMainBinding
import com.amanda.medicare.ui.camera.CameraActivity
import com.amanda.medicare.ui.onBoarding.OnboardFirstActivity
import com.amanda.medicare.ui.search.SearchActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentImageUri: Uri? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        val sharedPreferences = getSharedPreferences("onboarding", MODE_PRIVATE)
        val onboardingComplete = sharedPreferences.getBoolean("onboarding_complete", false)

        if (!onboardingComplete) {
            // If onboarding is not complete, start OnboardActivity1
            startActivity(Intent(this, OnboardFirstActivity::class.java))
            finish()
        }

        binding.btnCamera.setOnClickListener{startCamera()}
        binding.btnSearch.setOnClickListener{startCari()}
    }

    private fun isOnboardingCompleted(): Boolean {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("onboardingCompleted", false)
    }

    private fun setOnboardingCompleted() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("onboardingCompleted", true)
        editor.apply()
    }

    private fun startCari() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }


    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            val intent = Intent(this, CameraActivity::class.java)
            intent.putExtra("currentImageUri", currentImageUri)
            startActivity(intent)
        }
    }



    companion object {
        private const val REQUIRED_PERMISSION = android.Manifest.permission.CAMERA
    }

}