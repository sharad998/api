package com.example.udemy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal:Int): ViewModel() {

    ////LiveData usage....
    var total=MutableLiveData<Int>()

    init{
        total.value=startingTotal
    }

    fun setTotal(input:Int){
        //not...........
        //total.value+=input
        total.value = total.value?.plus(input)
    }

    ///LiveData usage ends...


    /*
      /////////////////--------not needed when using liveData--------////////////////////

    private var total=0
     init{
        total=startingTotal
    }
    fun getCurrentTotal(): Int{
        return total
    }

    fun getUpdatedTotal(i:Int){
        total+=i  //or count=+i
    }

     */

}