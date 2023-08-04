package com.example.gpsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    lateinit var textViewLat: TextView
    lateinit var textViewLong: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewLat = findViewById(R.id.textview_lat)
        textViewLong = findViewById(R.id.textView_long)
        button = findViewById(R.id.button)

        val locationSensor = LocationSensor(this)
        locationSensor.requestLocationPermission()
        locationSensor.start()

        locationSensor.lat.observe(this, Observer {
            textViewLat.text = "$it"
        })
        locationSensor.long.observe(this, Observer {
            textViewLong.text = "$it"
        })

        button.setOnClickListener {
            locationSensor.stop()
        }

    }
}