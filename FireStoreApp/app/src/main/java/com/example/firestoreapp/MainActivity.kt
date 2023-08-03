package com.example.firestoreapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    lateinit var button1: Button
    lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button_1)
        button2 = findViewById(R.id.button_2)

        val db = Firebase.firestore

        // POST
        button1.setOnClickListener {

            // POSTするデータ
            val breadcrumb: Breadcrumb = Breadcrumb(
                "abcdefg",
                Location(35.1, 135.0),
                listOf(
                    SNSProfile("twitter", "@xjp"),
                    SNSProfile("instagram", "instajp")
                ),
                "よろ",
                LocalDateTime.now().toString()
            )

            // POST
            db.collection("breadcrumb")
                .add(breadcrumb)
                .addOnSuccessListener { documentReference ->
                    Log.d("MainActivity", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("MainActivity", "Error adding document", e)
                }
        }

        // Get
        button2.setOnClickListener {
            db.collection("breadcrumb")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("MainActivity", "${document.id} => ${document.data}")

                        // java.lang.String
                        Log.d("check", "userId = ${document.data["userId"]} : ${document.data["userId"]?.javaClass?.name}")

                        // java.util.HashMap
                        Log.d("check", "location = ${document.data["location"]} : ${document.data["location"]?.javaClass?.name}")

                        // java.lang.String
                        Log.d("check", "profile = ${document.data["profile"]} : ${document.data["profile"]?.javaClass?.name}")

                        // java.util.ArrayList
                        Log.d("check", "snsProfiles = ${document.data["snsProfiles"]} : ${document.data["snsProfiles"]?.javaClass?.name}")
                        val snsProfiles: ArrayList<Any?>? = document.data?.get("snsProfiles") as ArrayList<Any?>?
                        Log.d("check", "snsProfiles[0] = ${snsProfiles?.get(0)} : ${snsProfiles?.get(0)?.javaClass?.name}")

                        // java.lang.String
                        Log.d("check", "createdAt = ${document.data["createdAt"]} : ${document.data["createdAt"]?.javaClass?.name}")

                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("MainActivity", "Error getting documents.", exception)
                }
        }

    }




}