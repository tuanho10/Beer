package com.example.beer.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.beer.data.model.DataDB

@Dao
interface Dao {
    @Insert
    fun insertFavorite(beer: DataDB)

    @Update
    fun updateFavorite(beer: DataDB)


    @Query("SELECT * FROM beer_information order by id asc")
    fun getAllSavedBeers(): LiveData<List<DataDB>>

    @Query("SELECT * FROM beer_information WHERE id =:id")
    fun getBeerById(id: String): DataDB

    @Query("DELETE FROM beer_information WHERE id =:id")
    fun deleteBeerById(id: String)

    @Delete
    fun deleteBeer(beer: DataDB)


}