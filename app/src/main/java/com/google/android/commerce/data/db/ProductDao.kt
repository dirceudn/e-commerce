package com.google.android.commerce.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.android.commerce.data.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Product>?)

    @Query("SELECT * FROM products")
    fun getLocalProducts(): LiveData<List<Product>>

}