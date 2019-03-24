package com.google.android.commerce.data.local.repository

import androidx.lifecycle.LiveData
import com.google.android.commerce.AppExecutors
import com.google.android.commerce.api.NetworkBoundResource
import com.google.android.commerce.data.db.ProductDao
import com.google.android.commerce.data.model.Product
import com.google.android.commerce.data.remote.ApiService
import com.google.android.commerce.helpers.Resource
import com.google.android.commerce.util.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject
constructor(
    private val appExecutors: AppExecutors,
    private val productDao: ProductDao,
    private val apiService: ApiService
) {

    private val postsListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)


    fun loadProducts(url: String): LiveData<Resource<List<Product>>> {
        return object : NetworkBoundResource<List<Product>, List<Product>>(appExecutors) {


            override fun saveCallResult(item: List<Product>) {
                productDao.insertAll(item)
            }

            override fun shouldFetch(data: List<Product>?): Boolean {
                return data == null || data.isEmpty() || postsListRateLimit.shouldFetch(url)
            }

            override fun loadFromDb() = productDao.getLocalProducts()

            override fun createCall() = apiService.getProducts(url)

            override fun onFetchFailed() {
                postsListRateLimit.reset(url)
            }
        }.asLiveData()
    }

}