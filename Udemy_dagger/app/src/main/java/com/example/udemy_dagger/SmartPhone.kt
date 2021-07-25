package com.example.udemy_dagger

import android.util.Log
import javax.inject.Inject


class SmartPhone @Inject constructor(battery:Battery,memoryCard:MemoryCard,simCard:SIMCard) {

    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailablity()
        Log.i("MYTAG","SmartPhone Constructed")
    }

    fun makeCallWithRecording(){
        Log.i("MYTAG","Calling......")
    }
}
