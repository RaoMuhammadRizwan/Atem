package com.example.atem.retrofit

import com.example.atem.pojo.mealList
import retrofit2.Call
import retrofit2.http.GET

interface retrofitInterface {

    @GET("random.php")
    fun getRandomMealInterface():Call<mealList>
}