package com.example.udemy_dagger

import android.util.Log
import javax.inject.Inject

class ServiceProvider @Inject constructor() {
    init {
        Log.i("MYTAG","serviceprovider constructed")
    }

    fun getServiceProvider(){
        Log.i("MYTAG","service provided....")
    }
}