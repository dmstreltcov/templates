package com.example.templates

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_COUNTER = "counter"
    lateinit var pref:SharedPreferences
    var counter:Int = 0

    lateinit var txtCounter:TextView
    lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        txtCounter = findViewById(R.id.counter)
        btn = findViewById(R.id.btn)

        btn.setOnClickListener{
            counter++
            txtCounter.text = counter.toString()
        }

    }

    override fun onResume() {
        super.onResume()
       if (pref.contains(APP_PREFERENCES_COUNTER)){
           counter = pref.getInt(APP_PREFERENCES_COUNTER, 0)
           txtCounter.text = counter.toString()
       }
    }

    override fun onPause() {
        super.onPause()
        val editor = pref.edit()
        editor.putInt(APP_PREFERENCES_COUNTER, counter)
        editor.apply()
    }
}
