package com.example.datekt

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.datekt.DatePickerFragment.DatePickerListener
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerListener {
    private var tvDisplayDate: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDisplayDate = findViewById(R.id.tvDisplaydate)
        val btnShowDatePicker =
            findViewById<Button>(R.id.btnShowDatePicker)
        btnShowDatePicker.setOnClickListener {
            val datePickerFragment: DialogFragment = DatePickerFragment()
            datePickerFragment.isCancelable = false
            datePickerFragment.show(supportFragmentManager, "date Picker")
        }
    }

    override fun onDateSet(
        datePicker: DatePicker?,
        year: Int,
        month: Int,
        day: Int
    ) {
        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = year
        cal[Calendar.MONTH] = month
        cal[Calendar.DAY_OF_MONTH] = day
        val date = DateFormat.getDateInstance().format(cal.time)
        tvDisplayDate!!.text = date
    }
}
