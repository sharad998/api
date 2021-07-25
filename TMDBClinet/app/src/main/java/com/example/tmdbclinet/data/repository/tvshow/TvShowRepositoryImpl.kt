package com.example.tmdbclinet.data.repository.tvshow

import android.util.Log

import com.example.tmdbclinet.data.model.tvshow.TVShow
import com.example.tmdbclinet.data.model.tvshow.TVShowList
import com.example.tmdbclinet.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclinet.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclinet.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclinet.domain.repository.TvShowRepository
import retrofit2.Response
import java.lang.Exception

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository {

    override suspend fun getTvShows(): List<TVShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TVShow>? {
        val newListOfTvShows= getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newListOfTvShows)
        tvShowCacheDataSource.saveTvShowsToCache(newListOfTvShows)
         return newListOfTvShows
    }

    suspend fun getTvShowsFromAPI():List<TVShow>{
        lateinit var tvShowList:List<TVShow>
        try {
            val response:Response<TVShowList> =tvShowRemoteDataSource.getTvShows()
            val body=response.body()
            if(body!=null){
                tvShowList=body.TVShows
            }

        }catch (exception:Exception){
            Log.i("MYTAG",exception.message.toString())

        }
        return  tvShowList
    }

    suspend fun getTvShowsFromDB():List<TVShow> {
        lateinit var tvShowList: List<TVShow>
        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB()
        } catch (exception: Exception) {
            Log.i("MYTAG", exception.message.toString())

        }
        if (tvShowList.size > 0) {
            return tvShowList
        }
        else{
            tvShowList=getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowList)
        }

        return tvShowList
    }

    suspend fun getTvShowsFromCache():List<TVShow>{
        lateinit var tvShowList: List<TVShow>
        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (exception: Exception) {
            Log.i("MYTAG", exception.message.toString())

        }
        if (tvShowList.size > 0) {
            return tvShowList
        }
        else{
            tvShowList=getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
        }

        return tvShowList

    }
}