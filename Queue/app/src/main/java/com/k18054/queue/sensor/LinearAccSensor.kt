package com.k18054.queue.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.k18054.queue.OtherFileStorage
import com.k18054.queue.Queue
import com.k18054.queue.TimeManager

class LinearAccSensor(private val context: Context): SensorEventListener, SensorBase {

    private lateinit var sensorManager: SensorManager
    private var AccSensor: Sensor? = null
    override val sensorName: String = "LinearAccSensor"
    override val queue: ArrayDeque<String> = ArrayDeque(listOf())
    override lateinit var otherFileStorage: OtherFileStorage

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        AccSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
    }

    override fun start(filename: String) {
        queue.clear()
        otherFileStorage = OtherFileStorage(context, "${filename}_${sensorName}", queue)
        otherFileStorage.saveAtBatch()
        sensorManager.registerListener(this, AccSensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun stop() {
        otherFileStorage.stop()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        queue.add("${TimeManager.getUnixTime()},${x},${y},${z}")
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

}