package com.example.finalapplication5.model.api

import com.example.finalapplication5.model.database.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostAPI {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}