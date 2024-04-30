package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemProductBinding

class ProductViewHolder(val viewBinding: ItemProductBinding): RecyclerView.ViewHolder(viewBinding.root) {

    // bind product to viewholder
    fun bind(product: Product) {
        viewBinding.product = product
    }
}