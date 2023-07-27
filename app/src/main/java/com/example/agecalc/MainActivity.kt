package com.example.agecalc

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView?=null

    private var tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button=findViewById(R.id.btnDatePicker)

        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener{

            clickDatePicker()

        }
    }


    private fun clickDatePicker(){

        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

    val dpd=  DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener()
        {view,selectedYear,selectedMonth,selectedDayofMonth ->

            Toast.makeText(this,
                "Year was $selectedYear,month was ${selectedMonth+1}" +
                        ", day of month was $selectedDayofMonth",

                    Toast.LENGTH_LONG).show()

            val selectedDate ="$selectedDayofMonth/${selectedMonth+1}/$selectedYear"

            tvSelectedDate?.text=selectedDate

            val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate=sdf.parse(selectedDate)
            theDate?.let {
                val selectedDateInMinutes=theDate.time/60000

                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {


                    val CurrentDateInMinutes = currentDate.time / 60000

                    val differnceInMinutes = CurrentDateInMinutes - selectedDateInMinutes

                    tvAgeInMinutes?.text = differnceInMinutes.toString()
                }
            }



        },
        year,
        month,
        day)

        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
//        dpd.show()


    }
}