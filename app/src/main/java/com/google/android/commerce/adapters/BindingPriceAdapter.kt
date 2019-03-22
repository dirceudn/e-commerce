package com.google.android.commerce.adapters

import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import java.text.NumberFormat
import java.util.*

object BindingPriceAdapter {


    @JvmStatic
    @BindingAdapter("bindSProductPrice")
    fun bindPrice(@NonNull textView: TextView, price: Long?) {

        val doublePayment: Long? = price
        val n = NumberFormat.getCurrencyInstance(Locale.FRANCE)
        val priceFormatted = n.format(doublePayment?.div(100.0))
        textView.text = priceFormatted
    }
}