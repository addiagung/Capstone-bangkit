package com.amanda.medicare.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.medicare.data.response.DataItem
import com.amanda.medicare.data.response.DataResponse
import com.amanda.medicare.data.retrofit.ApiConfig
import com.amanda.medicare.data.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(private val apiService: ApiService) : ViewModel() {
    private val _listMed = MutableLiveData<List<DataItem>>()
    val listMed: LiveData<List<DataItem>> = _listMed

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "SearchViewModel"
        private const val keyword = "infeksi"
    }

    fun setSearchQuery(keyword: String){
        search(keyword)
    }


    init {
        search(keyword)
    }

    private fun search(keyword: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().search(keyword)
        client.enqueue(object : Callback<DataResponse> {
            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    if (response != null) {
                        _listMed.value = response.body()?.data
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}