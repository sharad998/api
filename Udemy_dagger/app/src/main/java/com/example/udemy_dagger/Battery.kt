package com.example.udemy_dagger

import android.util.Log
import javax.inject.Inject

//  ------example for injecting interface dependency------
interface Battery {

    fun getPower()
}

//-----------------------------------------------


/*
//----class dependency example-----------

class Battery @Inject constructor(){
    init {
        Log.i("MYTAG","Battery Constructed")
    }

    fun getPower(){
        Log.i("MYTAG","Battery Power Is Available")

    }
}

 */