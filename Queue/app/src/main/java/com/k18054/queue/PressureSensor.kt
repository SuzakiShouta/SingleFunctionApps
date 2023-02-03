package com.k18054.queue

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class PressureSensor(context: Context, queue: Queue): SensorEventListener {

    val context = context
    val queue = queue
    private lateinit var sensorManager: SensorManager
    private var PreSensor: Sensor? = null

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        PreSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
    }

    fun start() {
        sensorManager.registerListener(this, PreSensor, SensorManager.SENSOR_DELAY_UI)
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        var pressure = event.values[0]
        queue.pressureQueue.add(TimeManager().getUnixTime().toString().plus(",").plus(pressure))
        Log.d("Pressure",queue.pressureQueue.size.toString())
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

}