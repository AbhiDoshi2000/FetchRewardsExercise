package com.example.fetchrewardsexercise

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardsexercise.network.FetchApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> get() = _items

    private val apiService: FetchApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(FetchApiService::class.java)
    }

    fun fetchItems() {
        viewModelScope.launch {
            try {
                val items = apiService.getItems()
                // Filter out null or blank names as before
                val filteredItems = items.filter { !it.name.isNullOrBlank() }
                _items.value = filteredItems.sortedBy { it.listId }.sortedBy { it.name }  // Sort by listId first, then name
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}