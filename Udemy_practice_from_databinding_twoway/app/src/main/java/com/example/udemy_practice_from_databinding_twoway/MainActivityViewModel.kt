package com.example.udemy_practice_from_databinding_twoway

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    val userName=MutableLiveData<String>()

    init{
        userName.value="Frank"
    }
}