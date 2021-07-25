package com.example.task

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(),
    DatePickerDialog.OnDateSetListener {
    interface DatePickerListener {
        fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, day: Int)
    }

    var mListener: DatePickerListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            context as DatePickerListener
        } catch (e: Exception) {
            throw ClassCastException(activity.toString() + " Must Implements DatePickerListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val day = cal[Calendar.DAY_OF_MONTH]
        return DatePickerDialog(activity!!, this, year, month, day)
    }

    override fun onDateSet(datePicker: DatePicker, i: Int, i1: Int, i2: Int) {
        mListener!!.onDateSet(datePicker, i, i1, i2)
    }
}