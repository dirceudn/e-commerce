package com.google.android.commerce.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "baskets")
@Parcelize
data class BasketItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @Embedded(prefix = "prod_")
    val product: Product,
    var quantity: Int = 0
): Parcelable