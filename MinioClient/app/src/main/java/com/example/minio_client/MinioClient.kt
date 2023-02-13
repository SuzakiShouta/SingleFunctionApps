package com.example.minio_client

import io.minio.MinioClient
import io.minio.UploadObjectArgs

class MinioClient {

    val accessKey = "アクセスキー"
    val secretKey = "シークレットキー"
    val bucketName = "バケットの名前"
    val endpoint = "https://~~~~"

    fun postFile(pass: String): String {
        val minioClient = MinioClient.builder()
            .endpoint(endpoint)
            .credentials(accessKey, secretKey)
            .build()

        val fileName = pass.substringAfterLast("/")

        try {
            minioClient.uploadObject(
                UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .`object`("projectId/$fileName.csv")
                    .filename(pass)
                    .build()
            )
            return "$endpoint/$bucketName/$fileName"
        } catch (e: Exception) {
            return "File upload failed: ${e.message}"
        }
    }
}