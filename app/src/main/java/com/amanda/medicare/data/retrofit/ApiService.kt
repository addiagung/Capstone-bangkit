package com.amanda.medicare.data.retrofit

import com.amanda.medicare.data.response.DataItem
import com.amanda.medicare.data.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET ("search")
    fun getData(@Query("keyword") keyword: String): Call<DataItem>

    @GET("search")
    fun search(@Query("keyword") keyword: String): Call<DataResponse>
}