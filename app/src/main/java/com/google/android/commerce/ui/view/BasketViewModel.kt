package com.google.android.commerce.ui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.android.commerce.AppCommerce
import com.google.android.commerce.data.local.repository.BasketRepository
import com.google.android.commerce.data.model.BasketItem
import javax.inject.Inject

class BasketViewModel : ViewModel() {

    @Inject
    lateinit var basketRepository: BasketRepository


    init {
        AppCommerce.appComponent.inject(this)

    }

    fun getProducts(): LiveData<List<BasketItem>> {
        return basketRepository.getBasketItems()
    }


}