package com.example.retrofit_coroutines_kotlin


//API Helper class to help us with the ApiService call
class ApiHelper(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUser()
}