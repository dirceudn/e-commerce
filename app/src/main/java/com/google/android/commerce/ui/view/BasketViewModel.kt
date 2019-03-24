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
    private var _basketItems: MutableLiveData<List<BasketItem>> = MutableLiveData()

    val cartsize: LiveData<Int> get() = _cartListSize
    val products: LiveData<List<BasketItem>> get() = _basketItems


    init {
        AppCommerce.appComponent.inject(this)


    }

    fun fetchProducts() {
        _basketItems.postValue(basketRepository.getCart())
        totalPrice()
    }

    fun fetchCarSize() {
        _cartListSize.postValue(basketRepository.getBasketSize())

    }

    fun addCartItem(item: BasketItem) {
        basketRepository.addBasketItem(item)
        _cartListSize.postValue(basketRepository.getBasketSize())
        _basketItems.postValue(basketRepository.getCart())


    }

    fun removeItem(item: BasketItem) {
        basketRepository.removeItem(item)
        _basketItems.postValue(basketRepository.getCart())

    }

    fun totalPrice(): Double? {
        return products.value?.fold(0.toDouble()) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.product.price!!.toDouble()) }
    }

    fun removeItemIncrement(item: BasketItem) {
        basketRepository.removeItemInc(item)
        _basketItems.postValue(basketRepository.getCart())

    }
}