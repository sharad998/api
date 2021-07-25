package com.example.udemy_coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class UserDataManager1 {
    suspend fun getTotalUserCount():Int{
        var count=0
        // below code does return 50 but the mani activity will receive value as 0
        // because the unstructured concurrency dose'nt assure the completion of child coroutine
        //or any other tasks before the parent suspend function completion.
        CoroutineScope(IO).launch {
            delay(1000)
            count=50
        }

        //but below code with async builder will run for unstructured concurrency

        val deferred =CoroutineScope(IO).async {
            delay(3000)
            return@async 70 //forcefull used instead of return
        }
        return count+ deferred.await()

        // however in both builder cases, launch and async, when using in unstructured concurrency, cannot assure proper handling of error or exception

        //solution for unstructured concurrency is use of structured Concurrency
        //coroutineScope--------------is a suspending function
        //CoroutineScope---------------is a interface
    }
}