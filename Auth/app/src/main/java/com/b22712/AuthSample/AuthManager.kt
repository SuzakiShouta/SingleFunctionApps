package com.b22712.AuthSample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


class AuthManager(private val activity: Activity) {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleSignInOptions: GoogleSignInOptions
    val RC_GOOGLE_SIGN_IN = 9001
    val clientId = "30742754069-duilel7cmtkb5cascvsmp91miph1uo91.apps.googleusercontent.com"

    fun initializeGoogleSignIn() {
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()

        Log.d("AuthLog","googleSignInOptions = $googleSignInOptions")
        googleSignInClient = GoogleSignIn.getClient(activity, googleSignInOptions)
        Log.d("AuthLog","googleSignInClient = $googleSignInClient")
    }

    fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
        Log.d("AuthLog","signInIntent = $signInIntent")
    }

    fun handleSignInResult(data: Intent?) {
        Log.d("AuthLog","handleSignInResult")
        val extras: Bundle = data!!.extras!!
        for (key in extras.keySet()) {
            val obj = extras[key]
            Log.d("AuthLog","Intent = $obj")
        }
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        Log.d("AuthLog","task = $task")
        try {
            val account = task.getResult(ApiException::class.java)
            account?.let {
                val idToken = it.idToken
                // Use idToken to authenticate with your own backend
                Log.d("AuthLog",idToken!!)
            }
        } catch (e: ApiException) {
            // Handle error
            Log.d("AuthLog", "signInResult: failed code=" + e.statusCode)
        }
    }
}
