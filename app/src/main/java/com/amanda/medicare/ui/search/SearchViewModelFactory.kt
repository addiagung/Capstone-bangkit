package com.amanda.medicare.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amanda.medicare.data.retrofit.ApiService

class SearchViewModelFactory(private val apiService: ApiService) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}