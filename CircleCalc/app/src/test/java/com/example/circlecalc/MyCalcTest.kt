package com.example.circlecalc


import com.google.common.truth.Truth.assertThat

import org.junit.Test

class MyCalcTest{

    private lateinit var myCalc: MyCalc
    // fun subjectUnderTest_actionOrInput_resultState()
    @Test
    fun calculateCircumference_radiusGiven_returnCorrectResult() {
        myCalc= MyCalc()
        val result=myCalc.calculateCircumference(2.1)
        assertThat(result).isEqualTo(13.188)
    }
}