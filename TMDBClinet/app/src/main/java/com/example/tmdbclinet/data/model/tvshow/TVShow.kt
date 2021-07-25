package com.example.tmdbclinet.data.model.tvshow

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_tvshows")
data class TVShow(

    @PrimaryKey val id: Int,

    val first_air_date: String,
    val name: String,
    val overview: String,
    val poster_path: String
)