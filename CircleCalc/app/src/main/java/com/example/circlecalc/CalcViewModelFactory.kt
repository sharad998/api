package com.example.circlecalc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CalcViewModelFactory(private val calculations: Calculations):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalcViewModel::class.java)) {
            return CalcViewModel(calculations) as T
        }

        ///.............................
        ///this is a standard template that can be used in any view model with few moifications
        throw IllegalArgumentException("Unknown View Model Class")
    }
}