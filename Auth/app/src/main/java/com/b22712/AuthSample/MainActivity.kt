package com.b22712.AuthSample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authManager = AuthManager(this)
        authManager.initializeGoogleSignIn()

        val signInButton = findViewById<com.google.android.gms.common.SignInButton>(R.id.sign_in_button)

        signInButton.setOnClickListener {
            authManager.signInWithGoogle()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("AuthLog","onActivityResult")
        Log.d("AuthLog","requestCode = $requestCode")
        Log.d("AuthLog","onActivityResult requestCode = $requestCode\nresultCode = $resultCode\ndata = $data")
        if (requestCode == authManager.RC_GOOGLE_SIGN_IN) {
            authManager.handleSignInResult(data)
        }
    }
}