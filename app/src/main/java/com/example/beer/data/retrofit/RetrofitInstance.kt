package com.example.beer.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val beerApi: BeerApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://apps.uthus.vn/api/api-testing/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }
}