package com.example.gpsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textview)
        button = findViewById(R.id.button)

        val locationSensor = LocationSensor(this)
        locationSensor.requestLocationPermission(this)
        locationSensor.fusedLocation()

        locationSensor.location.observe(this, Observer {
            textView.text = "${it.latitude}\n, ${it.longitude}"
        })

        button.setOnClickListener {
            locationSensor.fusedLocation()
        }

    }
}