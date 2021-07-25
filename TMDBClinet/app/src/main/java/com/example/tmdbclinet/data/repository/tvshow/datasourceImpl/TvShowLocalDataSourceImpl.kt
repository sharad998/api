package com.example.tmdbclinet.data.repository.tvshow.datasourceImpl

import com.example.tmdbclinet.data.db.TvShowDao
import com.example.tmdbclinet.data.model.tvshow.TVShow
import com.example.tmdbclinet.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//to work with local data base we need instance of dao interface
class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao): TvShowLocalDataSource {

    override suspend fun getTvShowsFromDB(): List<TVShow> =   tvShowDao.getTvShows()

    override suspend fun saveTvShowsToDB(tvShows: List<TVShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTvShows(tvShows)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTvShows()
        }
    }
}