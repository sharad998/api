package com.example.tmdbclinet.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.tmdbclinet.R
import com.example.tmdbclinet.databinding.ActivityHomeBinding
import com.example.tmdbclinet.presentation.artist.ArtistActivity
import com.example.tmdbclinet.presentation.movie.MovieActivity
import com.example.tmdbclinet.presentation.tvshow.TvShowActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_home)

        //clickable for movie
        binding.movieButton.setOnClickListener(View.OnClickListener {
            val intent=Intent(this,MovieActivity::class.java)
            startActivity(intent)
        })

        //clickable for tvshow
        binding.tvButton.setOnClickListener(View.OnClickListener {
            val intent=Intent(this, TvShowActivity::class.java)
            startActivity(intent)
        })

        //clickable for artist
        binding.artistsButton.setOnClickListener(View.OnClickListener {
            val intent=Intent(this, ArtistActivity::class.java)
            startActivity(intent)
        })
    }
}