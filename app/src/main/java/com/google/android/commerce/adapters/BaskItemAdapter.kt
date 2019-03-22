package com.google.android.commerce.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.google.android.commerce.R
import com.google.android.commerce.data.model.BasketItem

class BaskItemAdapter : RecyclerView.Adapter<BaskItemAdapter.BasketViewHolder>() {

    private var items: List<BasketItem>? = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaskItemAdapter.BasketViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_cart, parent, false)

        return BaskItemAdapter.BasketViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return if (items != null)
            items!!.size
        else
            0
    }

    fun setProductsList(list: List<BasketItem>){
        items = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaskItemAdapter.BasketViewHolder, position: Int) {
        holder.bind(items!![position])

    }


    class BasketViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Any) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()

        }

    }


}