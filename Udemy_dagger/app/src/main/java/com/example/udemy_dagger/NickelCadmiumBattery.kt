package com.example.udemy_dagger

import android.util.Log
import javax.inject.Inject

class NickelCadmiumBattery @Inject constructor() :Battery {
    override fun getPower() {

                Log.i("MYTAG","Battery Power Is Available")

            }
}