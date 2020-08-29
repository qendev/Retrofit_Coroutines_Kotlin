package com.example.retrofit_coroutines_kotlin

import model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUser(): List<User>
}