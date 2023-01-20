package com.example.beer.util

import android.app.Application
import androidx.lifecycle.*
import com.example.beer.data.db.DataDatabase
import com.example.beer.data.db.Repository
import com.example.beer.data.model.DataDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class Util(application: Application) : AndroidViewModel(application) {
    private var data: LiveData<List<DataDB>>
    private var repository: Repository

    init {
        val dao = DataDatabase.getInstance(application).dao()
        repository = Repository(dao)
        data = repository.beersList
    }

    fun insertBeer(beer: DataDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavoriteBeer(beer)
            withContext(Dispatchers.Main) {
            }
        }
    }

    fun updateBeer(beer: DataDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteBeer(beer)
            withContext(Dispatchers.Main) {
            }
        }
    }

    fun isBeerSavedInDatabase(id: String): Boolean {
        var meal: DataDB? = null
        runBlocking(Dispatchers.IO) {
            meal = repository.getBeerById(id)
        }
        if (meal == null)
            return false
        return true

    }

    fun deleteBeerById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBeerById(id)
        }
    }

    fun observeSaveBeer(): LiveData<List<DataDB>> {
        return data
    }
}