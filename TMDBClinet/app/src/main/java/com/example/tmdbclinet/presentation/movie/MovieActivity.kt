package com.example.tmdbclinet.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbclinet.R
import com.example.tmdbclinet.databinding.ActivityMovieBinding
import com.example.tmdbclinet.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMovieBinding
  private lateinit var movieViewModel: MovieViewModel
   @Inject
   lateinit var factory:MovieViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_movie)
        (application as Injector).createMovieSubComponent()
            .inject(this)

        movieViewModel=ViewModelProvider(this,factory).get(MovieViewModel::class.java)
        val responseLiveData=movieViewModel.getMovies()
        responseLiveData.observe(this,Observer {
            Log.i("MYTAG",it.toString())
        })

    }
}