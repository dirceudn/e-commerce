package com.google.android.commerce.injection

import com.google.android.commerce.AppExecutors
import com.google.android.commerce.data.db.ProductDb
import com.google.android.commerce.data.local.repository.BasketRepository
import com.google.android.commerce.data.local.repository.ProductRepository
import com.google.android.commerce.ui.view.BasketViewModel
import com.google.android.commerce.ui.view.ProductViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (DataModule::class)])
interface AppComponent {

    val journalDb: ProductDb

    val productRepository: ProductRepository

    val basketRepository: BasketRepository

    val appExecutor: AppExecutors

    fun inject(viewModel: ProductViewModel)

    fun inject(basketViewModel: BasketViewModel)
}