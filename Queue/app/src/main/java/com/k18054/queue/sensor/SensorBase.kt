package com.k18054.queue.sensor

import com.k18054.queue.OtherFileStorage

interface SensorBase {
    val sensorName: String
    val queue: ArrayDeque<String>
    val otherFileStorage: OtherFileStorage
    fun start(filename: String) {}
    fun stop() {}
}