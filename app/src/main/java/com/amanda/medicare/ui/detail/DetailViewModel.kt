package com.amanda.medicare.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.medicare.data.response.DataItem
import com.amanda.medicare.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _detailData = MutableLiveData<DataItem>()
    val detailData: LiveData<DataItem>  = _detailData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDetail(keyword: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getData(keyword)
        client.enqueue(object : Callback<DataItem> {
            override fun onResponse(
                call: Call<DataItem>,
                response: Response<DataItem>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    if (response != null) {
                        _detailData.value = response.body()
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DataItem>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object{
        const val TAG = "DetailViewModel"
    }
}