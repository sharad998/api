package com.example.tmdbclinet.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclinet.data.model.tvshow.TVShow

@Dao
interface TvShowDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)  //Room will replace old data with new data
        suspend fun saveTvShows(tvShows:List<TVShow>)

        @Query("DELETE FROM popular_tvshows")
        suspend fun deleteAllTvShows()

        @Query("SELECT * FROM popular_tvshows ")
        suspend fun getTvShows():List<TVShow>

}