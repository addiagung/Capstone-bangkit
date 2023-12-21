package com.amanda.medicare.ui.camera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amanda.medicare.databinding.ActivityCameraBinding
import com.amanda.medicare.ui.detail.DetailActivity

class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding
    private lateinit var viewModel: CameraViewModel
    private lateinit var medList: List<String>
    private lateinit var medResult: String

    private lateinit var classifier: Classifier
    private var currentImageUri: Uri? = null
    private val imageSize = 32

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentImageUri = intent.getParcelableExtra("currentImageUri")
        classifier = Classifier(this, "model_02.tflite")
        showImage()

        binding.btnDetect.setOnClickListener { detectImage() }
    }

    private fun detectImage() {

        currentImageUri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
            val predictedClass = classifier.classify(bitmap)
            Log.d("Image Classification", "Predicted Class: $predictedClass")

            val intent = Intent(this@CameraActivity, DetailActivity::class.java)
            intent.putExtra("predictedClass", predictedClass)
            startActivity(intent)
        }

    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }
}