package com.google.android.commerce.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.commerce.R
import com.google.android.commerce.adapters.BaskItemAdapter
import com.google.android.commerce.ui.view.BasketViewModel
import com.google.android.commerce.ui.view.ProductViewModel
import com.google.android.commerce.util.Constants
import kotlinx.android.synthetic.main.fragment_basket.*
import kotlinx.android.synthetic.main.fragment_home.*

class BasketFragment : Fragment() {

    lateinit var cartViewModel: BasketViewModel
    lateinit var cartAdapter: BaskItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_basket, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        cartAdapter = BaskItemAdapter()
        recycler_view_cart.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recycler_view_cart.adapter = cartAdapter


        attachData()
    }

    private fun attachData() {
        cartViewModel = ViewModelProviders.of(this).get(BasketViewModel::class.java)

        fetchItems()

    }

    private fun fetchItems() {

        cartAdapter.setProductsList(cartViewModel.getProducts())

    }
}