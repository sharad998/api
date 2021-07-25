package com.example.udemy_workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class UploadWorker(context: Context, workerParams: WorkerParameters) :Worker(context, workerParams) {
    override fun doWork(): Result {
       try {
           for (i in 0..60000) {
               Log.i("MYTAG", "Upload $i")
           }
           return Result.success()
       }
       catch (e:Exception){
           return Result.failure()
       }
    }
}