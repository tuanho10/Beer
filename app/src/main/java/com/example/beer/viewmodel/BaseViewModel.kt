package com.example.beer.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.beer.data.model.*
import com.example.beer.data.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "BaseViewModel"

class BaseViewModel : ViewModel() {
    private val mutableBeers = MutableLiveData<DataResponse>()

    init {
        getApiBeers()
    }

    private fun getApiBeers() {

        RetrofitInstance.beerApi.getBeers().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                mutableBeers.value = response.body()
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }

        })
    }

    fun observeBeers(): LiveData<DataResponse> {
        return mutableBeers
    }
}