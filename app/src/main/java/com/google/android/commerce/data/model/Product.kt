package com.google.android.commerce.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "products")
@Parcelize
data class Product(
    @PrimaryKey
    val ref: String,
    val title: String?,
    val description: String?,
    val thumbnail: String?,
    val price: Long?

) : Parcelable