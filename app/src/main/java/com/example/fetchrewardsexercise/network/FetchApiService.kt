package com.example.fetchrewardsexercise.network

import com.example.fetchrewardsexercise.Item
import retrofit2.http.GET

interface FetchApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}