package com.ag_apps.restaurants.domain.models

/**
 * @author Ahmed Guedmioui
 */
data class Restaurant (
    val name: String,
    val description: String,
    val image: String,
    val location: String,
    val latitude: Double,
    val longitude: Double
)