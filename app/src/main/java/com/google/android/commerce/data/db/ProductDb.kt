package com.google.android.commerce.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.google.android.commerce.data.model.BasketItem
import com.google.android.commerce.data.model.Product

@Database(entities = [Product::class, BasketItem::class], version = 1)
abstract class ProductDb : RoomDatabase() {
    abstract fun productDao(): ProductDao

}