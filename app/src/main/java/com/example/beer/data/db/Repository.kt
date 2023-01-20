package com.example.beer.data.db

import androidx.lifecycle.LiveData
import com.example.beer.data.model.DataDB

class Repository(private val dao: Dao) {

    val beersList: LiveData<List<DataDB>> = dao.getAllSavedBeers()

    fun insertFavoriteBeer(beer: DataDB) {
        dao.insertFavorite(beer)
    }

    fun updateFavoriteBeer(beer: DataDB) {
        dao.updateFavorite(beer)
    }

    fun getBeerById(beerId: String): DataDB {
        return dao.getBeerById(beerId)
    }

    fun deleteBeerById(beerId: String) {
        dao.deleteBeerById(beerId)
    }
}