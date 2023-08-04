package com.b22712.texttospeechapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(){

    lateinit var speechText: EditText
    lateinit var startButton: Button
    lateinit var stopButton: Button
    lateinit var speech: Speech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.button_start)
        stopButton = findViewById(R.id.button_stop)
        speechText = findViewById(R.id.speechText)
        speech = Speech(this)

        startButton.setOnClickListener {
            speech.speechText(speechText.text.toString())
        }

        stopButton.setOnClickListener {
            speech.stop()
        }

    }


}