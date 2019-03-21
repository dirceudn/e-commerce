package com.google.android.commerce.ui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.android.commerce.AppCommerce
import com.google.android.commerce.data.local.repository.ProductRepository
import com.google.android.commerce.data.model.Product
import com.google.android.commerce.util.SingleLiveEvent
import com.google.android.journal.data.model.Status
import javax.inject.Inject

class ProductViewModel : ViewModel() {

    @Inject
    lateinit var productRepository: ProductRepository
    private var _productsLiveData: MutableLiveData<List<Product>> = MutableLiveData()
    private val _showLoadingEvent = SingleLiveEvent<Boolean>()
    val isLoading: LiveData<Boolean> get() = _showLoadingEvent


    init {
        AppCommerce.appComponent.inject(this)

    }

    fun getProducts(url: String): LiveData<List<Product>> {
        return Transformations.switchMap(productRepository.loadProducts(url)) {
            it?.run {
                data?.run {
                    _productsLiveData.value = this
                }
                _showLoadingEvent.value = status == Status.LOADING

            }
            _productsLiveData
        }
    }

}