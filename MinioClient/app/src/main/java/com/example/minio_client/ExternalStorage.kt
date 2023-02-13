package com.example.minio_client

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.PrintWriter
import java.time.OffsetDateTime

class ExternalStorage(context: Context) {
    val fileAppend : Boolean = true //true=追記, false=上書き
    val context: Context = context
    val baceTime: OffsetDateTime = OffsetDateTime.now()
    var fileName : String = baceTime.toString()
    val extension : String = ".csv"
    // 適当に生成したSCVファイルのURL
    val filePath: String = context.applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString().plus("/").plus(fileName).plus(extension) //内部ストレージのDocumentのURL

    // 適当なファイルを外部ストレージに生成
    init {
        Log.d("ExternalStorage", filePath)
        writeText("time, x, y, z",filePath)
        writeText("$baceTime, 1.0, -2.0, 0.1",filePath)
        Log.d("ExternalStorage", "created")
    }

    private fun writeText(text:String, path:String){
        val fil = FileWriter(path,fileAppend)
        val pw = PrintWriter(BufferedWriter(fil))
        pw.println(text)
        pw.close()
    }
}