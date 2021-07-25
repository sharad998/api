package com.example.tmdbclinet.presentation.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.tmdbclinet.R
import com.example.tmdbclinet.databinding.ActivityTvShowBinding
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {
    lateinit var binding: ActivityTvShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
    }
}