package com.example.beer.data.retrofit

import com.example.beer.data.model.*
import retrofit2.Call
import retrofit2.http.GET

interface BeerApi {
    @GET("sample-data?page=1&limit=20")
    fun getBeers(): Call<DataResponse>
}