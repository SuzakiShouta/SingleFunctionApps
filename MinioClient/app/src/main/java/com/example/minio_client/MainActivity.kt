package com.example.minio_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val externalStorage = ExternalStorage(this)
        val minioClient = MinioClient()

        try {
            val url = minioClient.postFile(externalStorage.filePath)
            Log.d("minio", "url = $url")
        } catch (e: Exception) {
            Log.d("minio", "error = ${e.message}")
        }

    }
}