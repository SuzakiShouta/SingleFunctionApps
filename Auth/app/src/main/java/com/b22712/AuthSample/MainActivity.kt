package com.b22712.AuthSample


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private val receiver = CallGetTokenReceiver()

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, IntentFilter("call_getToken"))
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }

    private inner class CallGetTokenReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            getToken()
        }
    }

    fun getToken() {
        val application = applicationContext as MainApplication
        val oAuthFragment = OAuthFragment.newInstance()
        // Fragmentを作る
        supportFragmentManager.beginTransaction()
            .add(R.id.OAuthFragmentContainerView, oAuthFragment)
            .commit()
        val tokenReceivedListener = object : OAuthFragment.OnTokenReceivedListener {
            override fun onTokenReceived(token: String?) {
                // コールバックでtokenをもらう
                Log.d("MainActivity", "Token received: $token")
                // applicationにtokenを渡す．
                application.setToken(token)
                // Fragmentを消す
                supportFragmentManager.beginTransaction()
                    .remove(oAuthFragment)
                    .commit()
            }
        }
        oAuthFragment.setOnTokenReceivedListener(tokenReceivedListener as OAuthFragment.OnTokenReceivedListener)
    }

}