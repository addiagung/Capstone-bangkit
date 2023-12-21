package com.amanda.medicare.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.amanda.medicare.data.response.DataItem
import com.amanda.medicare.data.retrofit.ApiConfig
import com.amanda.medicare.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var adapter: MedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MedAdapter()
        adapter.notifyDataSetChanged()

        val searchViewModel = ViewModelProvider(this, SearchViewModelFactory(ApiConfig.getApiService())).get(
            SearchViewModel::class.java
        )

        supportActionBar?.hide()

        // Initialize SearchViewModel
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    //searchBar.text = searchView.text
                    searchView.hide()
                    val keyword = searchView.text.toString().trim()
                    if (keyword.isNotEmpty()) {
                        searchViewModel.setSearchQuery(keyword)
                    }
                    false
                }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        searchViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        searchViewModel.listMed.observe(this){itemsData ->
            setMedData(itemsData)
        }
    }

    private fun setMedData(med: List<DataItem>) {
        val adapter = MedAdapter()
        adapter.submitList(med)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}