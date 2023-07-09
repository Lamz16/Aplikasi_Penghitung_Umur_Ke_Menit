package com.asl.countyourage

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvSelectedDate : TextView
    private lateinit var tvAgeMinute : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDate : Button = findViewById(R.id.buttonDate)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeMinute = findViewById(R.id.tvMinute)
        btnDate.setOnClickListener{
            dateOnClick()
        }
    }
    fun dateOnClick(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val btnId : View = findViewById(R.id.buttonDate)
        val dpd = DatePickerDialog(this,
        {view,Year,Month,dayOfMonth ->

            Snackbar.make(this,btnId,"Tanggal $dayOfMonth-${Month+1}-$Year ", Snackbar.LENGTH_LONG).show()
            val selectedDate ="$dayOfMonth/${Month+1}/$Year"
            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val DateInMinute = theDate.time /60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time /60000
            val differenceInMinutes = currentDateInMinutes - DateInMinute

            tvAgeMinute.text = differenceInMinutes.toString()
        },
            year,
            month,
            day
            )
        dpd.datePicker.maxDate= System.currentTimeMillis() - 86400000
        dpd.show()
    }

}