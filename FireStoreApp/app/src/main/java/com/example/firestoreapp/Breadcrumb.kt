package com.example.firestoreapp

import android.location.Location

data class Breadcrumb(
    val userId: String,
    val location: com.example.firestoreapp.Location,
    val snsProfiles: List<SNSProfile>,
    val profile: String,
    val createdAt: String
)

data class SNSProfile (
    val type: String,
    val id: String
)

data class Location(
    val latitude: Double,
    val longitude: Double
)
