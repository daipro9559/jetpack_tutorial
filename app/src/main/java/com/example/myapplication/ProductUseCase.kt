package com.example.myapplication

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject


/**
Created by DaiNguyen on 14/04/2024.
 */
interface ProductUseCase {
    suspend fun fetchProducts(): List<Product>?
}

class ProductUseCaseImp @Inject constructor() : ProductUseCase {
    override suspend fun fetchProducts(): List<Product>? = withContext(Dispatchers.IO) {
        val url = URL("https://dummyjson.com/products")
        val connection = url.openConnection() as HttpURLConnection
        val inputStream = BufferedInputStream(connection.getInputStream())
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val bufferReader = BufferedReader(InputStreamReader(inputStream))
            var line: String? = null
            val stringBuilder = StringBuilder()
            while (bufferReader.readLine().let {
                    line = it
                    line != null
                }) {
                stringBuilder.append(line)
            }
           return@withContext Gson().fromJson(
                JSONObject(stringBuilder.toString()).optString("products"),
                object : TypeToken<ArrayList<Product>>() {}.type
            )
        } else {
          return@withContext  null
        }
    }

}