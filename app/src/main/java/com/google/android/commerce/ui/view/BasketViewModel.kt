package com.google.android.commerce.ui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.commerce.AppCommerce
import com.google.android.commerce.data.local.repository.BasketRepository
import com.google.android.commerce.data.model.BasketItem
import javax.inject.Inject

class BasketViewModel : ViewModel() {

    @Inject
    lateinit var basketRepository: BasketRepository
    private var _cartListSize: MutableLiveData<Int> = MutableLiveData()

    val carsize: LiveData<Int> get() = _cartListSize


    init {
        AppCommerce.appComponent.inject(this)


    }

    fun getProducts(): List<BasketItem> {
        return basketRepository.getCart()
    }

    fun fetchCarSize() {
        _cartListSize.postValue(basketRepository.getBasketSize())

    }

    fun addCartItem(item: BasketItem) {
        basketRepository.addBasketItem(item)
        _cartListSize.postValue(basketRepository.getBasketSize())

    }

}