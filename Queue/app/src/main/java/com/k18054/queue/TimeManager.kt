package com.k18054.queue

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeManager {

    companion object {
        fun getUnixTime(): Long {
            return System.currentTimeMillis()
        }

        fun getDateTimeString(): String {
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm")
            val formattedDateTime = currentDateTime.format(formatter)
            return formattedDateTime
        }

    }
}