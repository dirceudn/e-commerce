package com.google.android.commerce.injection

import android.app.Application
import androidx.room.Room
import com.google.android.commerce.data.db.ProductDao
import com.google.android.commerce.data.db.ProductDb
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    internal fun provideDb(app: Application): ProductDb {
        return Room.databaseBuilder(app, ProductDb::class.java, "commerce.db").fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    internal fun provideFeedDao(db: ProductDb): ProductDao {
        return db.productDao()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return Gson()
    }
}