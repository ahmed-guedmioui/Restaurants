package com.ag_apps.restaurants.data.restaurant

/**
 * @author Ahmed Guedmioui
 */
data class RestaurantDto (
    val name: String,
    val description: String,
    val image: String,
    val location: String,
    val latitude: Double,
    val longitude: Double
)