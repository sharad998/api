package com.example.tmdbclinet.presentation.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.tmdbclinet.R
import com.example.tmdbclinet.databinding.ActivityArtistBinding

class ArtistActivity : AppCompatActivity() {
   private lateinit var binding: ActivityArtistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_artist)
    }
}