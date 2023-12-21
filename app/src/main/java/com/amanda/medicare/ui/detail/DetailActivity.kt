package com.amanda.medicare.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.amanda.medicare.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val predictedClass = intent.getIntExtra("predictedClass", -1)
        Log.d("DetailActivity", "Received Predicted Class: $predictedClass")


        val keyword = intent.getStringExtra("keyword")
        val desc = intent.getStringExtra(EXTRA_DESC)
        val imagerUrl = intent.getStringExtra(EXTRA_URL)


        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        // Observe the data from ViewModel
        detailViewModel.detailData.observe(this) { data ->
            binding.tvDetailName.text = data.nama
            binding.tvDescMedicine.text = data.deskripsi
            binding.tvManfaatMedicine.text = data.manfaat
            binding.tvEfekMedicine.text = data.efekSamping
            binding.tvKategoriMedicine.text = data.kategori
            Picasso.get().load(data.linkGambar).into(binding.ivMedicine)
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        if (keyword != null) {
            detailViewModel.getDetail(keyword)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_URL = "extra_url"
    }
}