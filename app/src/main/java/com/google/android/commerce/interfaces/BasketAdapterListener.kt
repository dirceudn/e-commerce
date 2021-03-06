package com.google.android.commerce.interfaces

import com.google.android.commerce.data.model.BasketItem

interface BasketAdapterListener {

    fun removeBasketItem(item: BasketItem)

    fun removeItemInc(item: BasketItem)

    fun addItemInc(item: BasketItem)
}