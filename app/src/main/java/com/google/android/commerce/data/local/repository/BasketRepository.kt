package com.google.android.commerce.data.local.repository

import androidx.lifecycle.LiveData
import com.google.android.commerce.data.db.ProductDao
import com.google.android.commerce.data.model.BasketItem
import javax.inject.Inject

class BasketRepository @Inject
constructor(
    private val productDao: ProductDao
) {


    fun getBasketItems(): LiveData<List<BasketItem>> {
        return productDao.getBasketsItem()
    }

    fun addBasketItem(item: BasketItem) {
        val cartItem = productDao.findBasketByProduct(item.product.ref)
        if (cartItem == null) {
            item.quantity++
            productDao.insertBasketItem(item)
        } else {
            cartItem.quantity++
        }
        productDao.insertBasketItem(cartItem)

    }

    fun getBasketSize(): Int {
        var cartSize = 0
        val items = productDao.getBasketsItem()
        items.value?.forEach {
            cartSize += it.quantity
        }
        return cartSize
    }
}

