package com.example.udemy_coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class UserDataManager2 {
    suspend fun getTotalUserCount(): Int {
        var count = 0
        lateinit var deferred:Deferred<Int>

        coroutineScope {
            launch(IO) {
                //if no dispatcher mentioned, coroutine will rune in parent dispatcher
                // i.e. dispatcher from main activity
                delay(1000)
                count = 50
            }


            deferred = async(IO) {
                delay(3000)
                return@async 70 //forcefull used instead of return
            }
        }
            return count + deferred.await()

    }
}