package com.example.myapplication

import java.util.*

fun main(){
    print("enter number")

    var x =readLine()!!.toInt()

    val output= when(x%2){

        0 -> "$x is even"

        else -> "$x is odd"

    }
    println(output)

}