package com.example.myapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
Created by DaiNguyen on 21/04/2024.
 */
class Utils {
    fun sum(a: Int, b: Int) = a + b

    companion object {
        @JvmStatic
        @BindingAdapter("url")
        fun setUrl(imageView: ImageView, url: String?) {
            Glide.with(imageView.context).load(url ?: "").into(imageView)
        }
    }
}