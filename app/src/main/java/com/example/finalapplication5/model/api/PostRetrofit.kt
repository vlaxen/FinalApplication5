package com.example.finalapplication5.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostRetrofit {
    fun getRetrofit() =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostAPI::class.java)
}