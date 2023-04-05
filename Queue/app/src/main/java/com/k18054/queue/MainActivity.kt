package com.k18054.queue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.k18054.queue.sensor.LinearAccSensor
import com.k18054.queue.sensor.PressureSensor
import com.k18054.queue.sensor.SensorBase

class MainActivity : AppCompatActivity() {

    val queue:Queue = Queue()
    var targetSensors: MutableList<SensorBase> = mutableListOf()

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var fileNameText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.Start)
        stopButton = findViewById(R.id.Stop)
        fileNameText = findViewById(R.id.FileName)

        // 最初にstopボタンを押させない様にする
        stopButton.isEnabled = false

        // テキストボックスの初期値を入れとく
        fileNameText.setText(TimeManager.getDateTimeString())

        // 対象のセンサ（センシングするセンサ）
        targetSensors.add(PressureSensor(this))
        targetSensors.add(LinearAccSensor(this))

        startButton.setOnClickListener {

            sensorStart(targetSensors)

            stopButton.isEnabled = true
            startButton.isEnabled = false
        }

        stopButton.setOnClickListener {

            sensorStop(targetSensors)

            startButton.isEnabled = true
            stopButton.isEnabled = false
        }

    }

    private fun sensorStart(sensors: MutableList<SensorBase>) {
        for (sensor in sensors) {
            sensor.start(fileNameText.text.toString())
            Log.d("MainActivity", "fileName = ${fileNameText.text}")
        }
    }

    private fun sensorStop(sensors: MutableList<SensorBase>) {
        for (sensor in sensors) {
            sensor.stop()
        }
    }

}