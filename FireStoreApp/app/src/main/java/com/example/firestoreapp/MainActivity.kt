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

        button1.setOnClickListener {

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

            db.collection("breadcrumb")
                .add(breadcrumb)
                .addOnSuccessListener { documentReference ->
                    Log.d("MainActivity", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("MainActivity", "Error adding document", e)
                }
        }

        button2.setOnClickListener {
            db.collection("breadcrumb")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("MainActivity", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("MainActivity", "Error getting documents.", exception)
                }
        }

    }




}