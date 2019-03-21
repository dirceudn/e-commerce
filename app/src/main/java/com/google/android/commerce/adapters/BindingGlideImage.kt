package com.google.android.commerce.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingGlideImage {

    @JvmStatic
    @BindingAdapter("bindGlideImage")
    fun setImageUrl(view: ImageView, url: String?) {
        Glide.with(view.context).load(url).into(view)
    }

}