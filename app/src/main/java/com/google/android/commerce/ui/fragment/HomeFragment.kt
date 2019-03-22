package com.google.android.commerce.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.commerce.R
import com.google.android.commerce.adapters.ProductAdapter
import com.google.android.commerce.data.model.BasketItem
import com.google.android.commerce.data.model.Product
import com.google.android.commerce.interfaces.ProductAdapterListener
import com.google.android.commerce.ui.view.BasketViewModel
import com.google.android.commerce.ui.view.ProductViewModel
import com.google.android.commerce.util.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : Fragment(), ProductAdapterListener {


    lateinit var productViewModel: ProductViewModel
    lateinit var cartViewModel: BasketViewModel

    lateinit var productAdapter: ProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<FloatingActionButton>(R.id.basketButton).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_title_screen_to_register)
        }
        return view

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        productAdapter = ProductAdapter(this)
        recycler_view.layoutManager = GridLayoutManager(activity, 2)
        recycler_view.adapter = productAdapter

        attachData()
    }

    private fun attachData() {

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        cartViewModel = ViewModelProviders.of(this).get(BasketViewModel::class.java)

        productViewModel.isLoading.observe(this, Observer { value -> refresh.isRefreshing = value ?: false })

        fetchProducts()

        refresh.setOnRefreshListener {
            fetchProducts()
        }

        cartViewModel.fetchCarSize()

        cartViewModel.carsize
            .observe(viewLifecycleOwner, Observer { size ->
                cart_size.text = size.toString()
            })


    }

    private fun fetchProducts() {
        productViewModel.getProducts(Constants.INSTANCE.PRODUCTS)
            .observe(viewLifecycleOwner, Observer { posts ->
                productAdapter.setProductList(posts)
            })
    }

    override fun onProductSelected(product: Product) {
        val basketItem = BasketItem(product = product)
        cartViewModel.addCartItem(basketItem)
    }

}