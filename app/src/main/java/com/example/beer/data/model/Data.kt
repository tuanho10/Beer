package com.example.beer.data.model

import androidx.room.Entity

@Entity(tableName = "beer")
data class Data(
    val id: String,
    val name: String,
    val price: String,
    val image: String,
    var note: String
)