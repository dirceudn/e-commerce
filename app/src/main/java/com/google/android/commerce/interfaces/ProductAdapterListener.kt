package com.google.android.commerce.interfaces

import com.google.android.commerce.data.model.Product

interface ProductAdapterListener {

    fun onProductSelected(product: Product)

}