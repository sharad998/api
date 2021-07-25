package com.example.udemy_dagger

import android.util.Log
import javax.inject.Inject

//------------consider it AS THIRD PARTY class which doesnt allow injection in our classes----------
//------hence we create modules for below class and pass this module to SmartPhoneComponent
class MemoryCard constructor(){
    init {
        Log.i("MYTAG","MemoryCard constructed")
    }

    fun getSpaceAvailablity(){
        Log.i("MYTAG","Space is Available")

    }
}

/* ------------for direct injection----------------
class MemoryCard @Inject constructor(){
    init {
        Log.i("MYTAG","MemoryCard constructed")
    }

    fun getSpaceAvailablity(){
        Log.i("MYTAG","Space is Available")

    }
}

 */