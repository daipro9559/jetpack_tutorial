package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by DaiNguyen on 14/04/2024.
 */
@HiltViewModel
class MyViewModel @Inject constructor(private val productUseCase: ProductUseCase): ViewModel(){
    private val _productLiveData: MutableLiveData<List<Product>> = MutableLiveData()
    val product: LiveData<List<Product>> = liveData {
      productUseCase.fetchProducts()?.also {
          emit(it)
      }
    }

    suspend fun fetchProduct() = productUseCase.fetchProducts()
}