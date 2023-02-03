package com.example.letslavlusapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token: String = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwNWI0MDljNmYyMmM0MDNlMWY5MWY5ODY3YWM0OTJhOTA2MTk1NTgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoi6aCI5bSO57-U5aSqIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FFZEZUcDZJVmdMbUhVazRTMW42ZDBGLUZva1VPcUM2R2R1TFdwejk1ZThNPXM5Ni1jIiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL2xhdmx1cyIsImF1ZCI6Imxhdmx1cyIsImF1dGhfdGltZSI6MTY3NTM5MzQ0OCwidXNlcl9pZCI6Ijc2U2tVMEVsUFpjck5iRnBKVk9LU2dIVGthcTIiLCJzdWIiOiI3NlNrVTBFbFBaY3JOYkZwSlZPS1NnSFRrYXEyIiwiaWF0IjoxNjc1MzkzNDQ4LCJleHAiOjE2NzUzOTcwNDgsImVtYWlsIjoibG9nLnN1emFraUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjExNTE1OTM5NTU2MDA2OTUwNzQzMCJdLCJlbWFpbCI6WyJsb2cuc3V6YWtpQGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6Imdvb2dsZS5jb20ifX0.pQievsfgp6s-GU4uAgkyM9lHROpw7n6Wkj8dScyIfQbH4MDx7-UvV4PX0Yfb5mMeQNXhE7pUh5zGAOV9ilEv39wj-bu2mlFycQk7nSgWpG9L3hJ_zq0Ryi7C7iVzGIs4iP-PYmvpIe_BBHZhjOI_gVpJf6sADA6MPA0Rxm6leCcetGE5lGDV9PSkDOx5VAjMspGmXBtpee_jwiceCSUPWxaxjwVwfCvhxGjcRkw4MbLMt_2pRkmYpmv4U56OkL0AwBTUdJYWp4kvRXE4zrGixQOsv1U0WpNEjspPorj6xMP0JUVeNUmOr54COL4vi-v2XBsPYU3pflbenjTaTxyspA"
        Log.d("Let's Lavlus Api","token = $token")
        val authInterceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization","Bearer $token")
                .build()
            chain.proceed(newRequest)
        }
        Log.d("Let's Lavlus Api","authInterceptor = $authInterceptor!")
        val projectApi = LavlusApi(authInterceptor)
        val call = projectApi.getProjects()
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("Let's Lavlus Api","onResponse!")
                Log.d("Let's Lavlus Api","call = $call!")
                Log.d("Let's Lavlus Api","response = $response!")
                if (response.isSuccessful) {
                    // handle successful response
                    val result = response.body()
                    Log.d("Let's Lavlus Api","isSuccessful!")
                    Log.d("Let's Lavlus Api","$result")
                } else {
                    // handle error response
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // handle failure
                Log.d("Let's Lavlus Api","onFailure!")
                Log.d("Let's Lavlus Api","$call")
            }
        })

    }
}