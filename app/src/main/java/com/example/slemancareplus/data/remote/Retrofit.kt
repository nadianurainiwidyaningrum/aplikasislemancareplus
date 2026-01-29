package com.example.slemancareplus.data.remote

import com.example.slemancareplus.api.ApiService
import retrofit2.converter.gson.GsonConverterFactory
import com.example.slemancareplus.data.remote.RetrofitClient
import retrofit2.Retrofit

object RetrofitClient {

    private const val BASE_URL = "http://192.168.0.102/api/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
