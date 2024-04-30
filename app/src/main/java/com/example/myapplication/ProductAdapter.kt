package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemProductBinding

class ProductAdapter(val item: List<Product>?): RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = item?.size ?: 0

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        item?.get(position)?.let { holder.bind(it) }
    }

}