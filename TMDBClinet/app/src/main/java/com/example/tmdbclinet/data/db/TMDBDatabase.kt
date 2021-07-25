package com.example.tmdbclinet.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbclinet.data.model.artist.Artist
import com.example.tmdbclinet.data.model.movie.Movie
import com.example.tmdbclinet.data.model.tvshow.TVShow

@Database(entities = [Movie::class,TVShow::class, Artist::class]
    ,version = 1
    ,exportSchema = false)
abstract class TMDBDatabase:RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvShowDao
    abstract fun artistDao(): ArtistDao

}