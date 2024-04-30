package com.example.myapplication

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
Created by DaiNguyen on 14/04/2024.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
 @Binds
 abstract fun bindProductUseCase(productUseCaseImp: ProductUseCaseImp): ProductUseCase
}