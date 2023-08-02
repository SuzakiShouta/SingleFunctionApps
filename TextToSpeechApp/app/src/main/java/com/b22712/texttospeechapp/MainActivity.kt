package com.b22712.texttospeechapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.util.Locale

class MainActivity : AppCompatActivity(){

    lateinit var speechText: EditText
    lateinit var button: Button
    lateinit var speech: Speech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        speechText = findViewById(R.id.speechText)
        speech = Speech(this)

        button.setOnClickListener {
            speech.speechText(speechText.text.toString())
        }

    }


}