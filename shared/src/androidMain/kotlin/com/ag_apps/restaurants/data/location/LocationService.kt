package com.ag_apps.restaurants.data.location

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