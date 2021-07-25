package com.example.udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.udemy.databinding.ActivityMainForViewModelBinding

class MainActivityForViewModel : AppCompatActivity() {
    private  lateinit var binding:ActivityMainForViewModelBinding
    private lateinit var viewModel:MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main_for_view_model)
        viewModelFactory= MainActivityViewModelFactory(125)
        viewModel=ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel::class.java)


        viewModel.total.observe(this, Observer {
            binding.countText.text =it.toString()
        })

        binding.button.setOnClickListener(View.OnClickListener {
            viewModel.setTotal(binding.EnterText.text.toString().toInt())

        })



        /*
        --------------not needed when using LiveData--------------------

        binding.countText.text = viewModel.getCurrentTotal().toString()

        binding.button.setOnClickListener(View.OnClickListener {
           viewModel.getUpdatedTotal(binding.EnterText.text.toString().toInt()).toString()
            binding.countText.text=viewModel.getCurrentTotal().toString()

        })

         */



    }
}