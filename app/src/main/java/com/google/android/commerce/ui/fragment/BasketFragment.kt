package com.google.android.commerce.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.commerce.R
import com.google.android.commerce.adapters.BaskItemAdapter
import com.google.android.commerce.data.model.BasketItem
import com.google.android.commerce.interfaces.BasketAdapterListener
import com.google.android.commerce.ui.view.BasketViewModel
import kotlinx.android.synthetic.main.fragment_basket.*
import java.text.NumberFormat
import java.util.*


class BasketFragment : Fragment(), BasketAdapterListener {



    lateinit var cartViewModel: BasketViewModel
    lateinit var cartAdapter: BaskItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_basket, container, false)

    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cartAdapter = BaskItemAdapter(this)
        recycler_view_cart.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recycler_view_cart.adapter = cartAdapter

        attachData()
    }

    private fun attachData() {
        cartViewModel = ViewModelProviders.of(this).get(BasketViewModel::class.java)

        fetchItems()

    }

    private fun fetchItems() {

        cartViewModel.fetchProducts()

        cartViewModel.products.observe(viewLifecycleOwner, androidx.lifecycle.Observer { values ->
            cartAdapter.setProductsList(values)
            val totalPrice = cartViewModel.totalPrice()
            if (totalPrice != null) {
                formatPrice(totalPrice)

            }
        })

    }


    private fun formatPrice(price: Double) {
        val n = NumberFormat.getCurrencyInstance(Locale.FRANCE)
        val priceFormatted = n.format(price.div(100.0))
        total_price.text = priceFormatted
    }

    override fun removeBasketItem(item: BasketItem) {
        cartViewModel.removeItem(item)

    }

    override fun removeItemInc(item: BasketItem) {
        cartViewModel.removeItemIncrement(item)
    }

    override fun addItemInc(item: BasketItem) {
        cartViewModel.addCartItem(item)

    }
}