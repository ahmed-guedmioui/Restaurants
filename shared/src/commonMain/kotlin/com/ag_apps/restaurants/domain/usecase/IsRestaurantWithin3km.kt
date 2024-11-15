package com.ag_apps.restaurants.domain.usecase

import com.ag_apps.restaurants.domain.models.Restaurant
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * @author Ahmed Guedmioui
 */

fun Restaurant.isRestaurantWithin3km(
    userLatitude: Double, userLongitude: Double
): Boolean {

    val distance = haversineDistance(
        userLatitude, userLongitude, this.latitude, this.longitude
    )

    return distance <= 3.0
}

private fun haversineDistance(
    lat1: Double, lon1: Double, lat2: Double, lon2: Double
): Double {
    val earthRadiusKm = 6371.0

    val dLat = (lat2 - lat1).toRadians()
    val dLon = (lon2 - lon1).toRadians()

    val a = sin(dLat / 2).pow(2) +
            cos(lat1.toRadians()) * cos(lat2.toRadians()) *
            sin(dLon / 2).pow(2)

    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return earthRadiusKm * c
}

private fun Double.toRadians(): Double = this * PI / 180