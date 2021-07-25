package com.example.tmdbclinet.data.model.artist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_artists")   //best practice room database table should be diffrent from data class name or by defaultv its same
data class Artist(


    @PrimaryKey val id: Int,

    val name: String,
    val popularity: Double,
    val profile_path: String
)