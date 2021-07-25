package com.example.udemy_dagger

import android.util.Log
import javax.inject.Inject

// in kotlin primary constructor is invisible, to make it visible we have to explicitly write word constructor
class SIMCard @Inject constructor (private val serviceProvider: ServiceProvider) {
    init {
        Log.i("MYTAG","SIMCard constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}


