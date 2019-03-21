package com.google.android.commerce.data.remote

import androidx.lifecycle.LiveData
import com.google.android.commerce.api.ApiResponse
import com.google.android.commerce.api.LiveDataCallAdapterFactory
import com.google.android.commerce.data.model.Product
import com.google.android.commerce.util.Constants
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {


    @GET
    fun getProducts(@Url url: String): LiveData<ApiResponse<List<Product>>>


    companion object Factory {
        fun create(): ApiService {
            val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()

            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.INSTANCE.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}