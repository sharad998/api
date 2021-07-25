package com.example.udemy

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.udemy.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var databinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding=DataBindingUtil.setContentView(this,R.layout.activity_main)

      /* val button=databinding.save
         button.setOnClickListener(View.OnClickListener {
            greeting()
        })

       */
        // ....................OR.............................


        databinding.save.setOnClickListener(View.OnClickListener {
            greeting()
        })

        databinding.progress.setOnClickListener(View.OnClickListener {
            if (databinding.progressBar.visibility==View.GONE){
                databinding.progressBar.visibility=View.VISIBLE
                databinding.progress.text = "stop"
                databinding.progress.setBackgroundColor(Color.RED)
            }
            else  if(databinding.progressBar.visibility==View.VISIBLE){
                databinding.progressBar.visibility=View.GONE
                databinding.progress.text="start"
                databinding.progress.setBackgroundColor(Color.GREEN)
            }

        })

        databinding.student=getStudent()
    }

    private fun greeting(){
        val text=databinding.textView
        //var ran:Random= Random
       // var ran1=Random.nextInt(255)

        text.text = "Bonjour....you clicked!!!!"
        text.setTextColor(Color.rgb(Random.nextInt(255),Random.nextInt(255),Random.nextInt(255)))
        databinding.save.setBackgroundColor(Color.rgb(Random.nextInt(255),Random.nextInt(255),Random.nextInt(255)))
        text.textSize= 32.0F


    }

    private fun getStudent():Student{
        return Student(1, "JAVIER","javier.harvard.ac.soc")
    }
}

class Student(i:Int,n:String,e:String) {
    val id=i
    val name=n
    val email=e



}



