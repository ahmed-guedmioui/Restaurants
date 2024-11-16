package com.ag_apps.restaurants.data.location

import com.ag_apps.restaurants.domain.models.Location
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLDistanceFilterNone
import platform.CoreLocation.kCLLocationAccuracyBest
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlin.concurrent.AtomicReference
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @author Ahmed Guedmioui
 */
actual class LocationService  {

    private val locationManager = CLLocationManager()
    private val latestLocation = AtomicReference<Location?>(null)

    private class LocationDelegate : NSObject(), CLLocationManagerDelegateProtocol {
        var onLocationUpdate: ((Location?) -> Unit)? = null

        @OptIn(ExperimentalForeignApi::class)
        override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
            didUpdateLocations.firstOrNull()?.let {
                val location = it as CLLocation
                location.coordinate.useContents {
                    onLocationUpdate?.invoke(Location(latitude, longitude))
                }

            }
        }

        override fun locationManager(manager: CLLocationManager, didFailWithError: NSError) {
            onLocationUpdate?.invoke(null)
        }
    }


    actual suspend fun getCurrentLocation(): Location? = suspendCoroutine { continuation ->
        locationManager.requestWhenInUseAuthorization()
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.distanceFilter = kCLDistanceFilterNone
        locationManager.startUpdatingLocation()

        val locationDelegate = LocationDelegate()
        locationDelegate.onLocationUpdate = { location ->
            locationManager.stopUpdatingLocation()
            latestLocation.value = location
            if (location != null) {
                continuation.resume(location)
            } else {
                continuation.resume(null)
            }
        }

        locationManager.delegate = locationDelegate
    }
}