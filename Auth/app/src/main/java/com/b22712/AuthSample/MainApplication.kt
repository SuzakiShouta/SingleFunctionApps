package com.b22712.AuthSample

import android.app.Application
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainApplication: Application() {

    private val _token = MutableLiveData<String?>(null)
    val token: LiveData<String?> = _token
    fun setToken(token: String?) {
        _token.postValue(token)
    }

    fun getToken(){
        // MainActivityに関数を実行してもらう
        val intent = Intent("call_getToken")
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }

}