package com.example.retrofit_coroutines_kotlin

class MainRepository(private val apiService: ApiHelper) {
    suspend fun getUsers() = apiService.getUsers()
}