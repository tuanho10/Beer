package com.example.beer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer_information")
data class DataDB(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: String,
    val image: String,
    val note: String
)
