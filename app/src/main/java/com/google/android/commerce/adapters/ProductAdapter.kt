package com.google.android.commerce.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.google.android.commerce.R
import com.google.android.commerce.data.model.Product
import com.google.android.commerce.interfaces.ProductAdapterListener

class ProductAdapter(listener: ProductAdapterListener) : RecyclerView.Adapter<ProductAdapter.PostViewHolder>() {

    private var productListener: ProductAdapterListener = listener
    private var products: List<Product>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_product, parent, false)

        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (products != null)
            products!!.size
        else
            0
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(products!![position], productListener)

    }

    fun setProductList(list: List<Product>?) {
        products = list
        notifyDataSetChanged()
    }

    class PostViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Any, productListener: ProductAdapterListener?) {
            binding.setVariable(BR.data, data)
            binding.setVariable(BR.listener, productListener)
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                productListener?.onProductSelected(data as Product)

            }
        }

    }
}