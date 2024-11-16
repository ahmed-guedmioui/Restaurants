package com.ag_apps.restaurants.domain

import android.annotation.SuppressLint
import android.content.Context
import com.ag_apps.restaurants.domain.models.Location
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @author Ahmed Guedmioui
 */
actual class LocationService(
    private val context: Context
) {

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    actual suspend fun getCurrentLocation(): Location? = suspendCoroutine { continuation ->
//        continuation.resume(Location(33.545672, -7.674557))
//        return@suspendCoroutine

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                continuation.resume(Location(it.latitude, it.longitude))
            } ?: run {
                continuation.resume(null)
            }
        }.addOnFailureListener { e ->
            continuation.resume(null)
        }
    }
}