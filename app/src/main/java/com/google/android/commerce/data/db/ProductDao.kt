package com.google.android.commerce.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.android.commerce.data.model.BasketItem
import com.google.android.commerce.data.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Product>?)

    @Query("SELECT * FROM products")
    fun getLocalProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBasketItem(item: BasketItem?)


    @Query("SELECT * FROM baskets")
    fun getBasketsItem(): LiveData<List<BasketItem>>

    @Query("SELECT * FROM baskets WHERE prod_ref =:id")
    fun findBasketByProduct(id: String): BasketItem

}