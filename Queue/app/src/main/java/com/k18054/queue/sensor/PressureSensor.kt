package com.k18054.queue.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.k18054.queue.OtherFileStorage
import com.k18054.queue.Queue
import com.k18054.queue.TimeManager

class PressureSensor(private val context: Context): SensorEventListener, SensorBase {

    private lateinit var sensorManager: SensorManager
    private var PreSensor: Sensor? = null
    override val sensorName: String = "PressureSensor"
    override val queue: ArrayDeque<String> = ArrayDeque(listOf())
    override lateinit var otherFileStorage: OtherFileStorage
    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        PreSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
    }

    override fun start(filename: String) {
        queue.clear()
        otherFileStorage = OtherFileStorage(context, "${filename}_${sensorName}", queue)
        otherFileStorage.saveAtBatch()
        sensorManager.registerListener(this, PreSensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun stop() {
        otherFileStorage.stop()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val pressure = event.values[0]
        queue.add(TimeManager.getUnixTime().toString().plus(",").plus(pressure))
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

}