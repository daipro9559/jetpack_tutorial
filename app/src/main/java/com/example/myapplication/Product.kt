package com.example.myapplication

/**
Created by DaiNguyen on 14/04/2024.
 */
class Product(
 val id: Int,
 val title: String,
 val description: String,
 val price: Float,
 val discountPercentage: Double,
 val rating: Double,
 val stock: Int,
 val brand: String,
 val category: String,
 val thumbnail: String,
 val images: List<String>
)