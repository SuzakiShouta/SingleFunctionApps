package com.example.gpsapp

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationSensor(val activity: MainActivity) {

    private val _location: MutableLiveData<Location> = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    fun requestLocationPermission(activity: Activity) {
        val LOCATION_PERMISSION_REQUEST_CODE = 1001

        val isAccept = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!isAccept) {
            // 権限が許可されていない場合はリクエストする
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    fun fusedLocation() {

        // 最後に確認された位置情報を取得
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(activity)
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("LocationSensor","権限がない")
            // 権限もらえないと困っちゃうなぁ
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener(activity) { location ->
                Log.d("LocationSensor","$location")
                if (location != null) {
                    // Logic to handle location object
                    // 緯度の表示
                    val str1 = "Latitude:" + location.latitude

                    // 経度の表示
                    val str2 = "Longitude:" + location.longitude
                    _location.postValue(location)
                }
            }
    }

}