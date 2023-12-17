package com.amanda.medicare.ui.camera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amanda.medicare.databinding.ActivityCameraBinding
import com.amanda.medicare.ui.detail.DetailActivity

class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentImageUri = intent.getParcelableExtra("currentImageUri")
        showImage()

        binding.btnDetect.setOnClickListener { detectImage() }
    }

    private fun detectImage() {
        val intent = Intent(this@CameraActivity, DetailActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }

}