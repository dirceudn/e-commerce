package com.google.android.commerce.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BasketItem(
    val product: Product,
    var quantity: Int = 0
): Parcelable