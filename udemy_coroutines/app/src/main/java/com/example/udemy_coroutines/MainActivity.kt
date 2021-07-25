package com.example.udemy_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.udemy_coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    public var count=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.download.setOnClickListener(View.OnClickListener {
            CoroutineScope(Dispatchers.IO).launch { downloadUser() }
        })

        binding.click.setOnClickListener(View.OnClickListener {
            count += 1
            binding.text.text = count.toString()
        })


    }

    // FOR MOVING THE OUTPUT TO DIFFERENT, (HERE MAIN THREAD), withContext is used
    // withContext is a suspend function and suspend function cannot be called from normal function
    // //and hence the we have to add suspend modifier to the normal function to do so
    private suspend fun downloadUser(){
        for (i in 1..200000){
            withContext(Dispatchers.Main) {
                binding.downloadingText.text = "downloading user $i in ${Thread.currentThread().name}"

            }
            }



    }

   /* do run in background without changing the thread

   private fun downloadUser(){
        for (i in 1..200000){
            Log.i("reckon","downloading user $i in ${Thread.currentThread().name}")
        }

    */


    }
