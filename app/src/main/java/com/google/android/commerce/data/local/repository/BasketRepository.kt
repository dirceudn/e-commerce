package com.google.android.commerce.data.local.repository

import androidx.lifecycle.MutableLiveData
import com.google.android.commerce.data.db.ProductDao
import com.google.android.commerce.data.model.BasketItem
import io.paperdb.Paper
import javax.inject.Inject

class BasketRepository @Inject
constructor(
) {


    fun getBasketSize(): Int {
        var cartSize = 0
        getCart().forEach {
            cartSize += it.quantity
        }

        return cartSize
    }


    fun addBasketItem(cartItem: BasketItem) {
        val cart = getCart()

        val targetItem = cart.singleOrNull { it.product.ref == cartItem.product.ref }

        if (targetItem == null) {
            cartItem.quantity++
            cart.add(cartItem)
        } else {

            targetItem.quantity++
        }
        saveCart(cart)

    }

    fun getCart(): MutableList<BasketItem> {
        return Paper.book().read("cart", mutableListOf())
    }

    fun saveCart(cart: MutableList<BasketItem>) {
        Paper.book().write("cart", cart)
    }
}

