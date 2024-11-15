package com.ag_apps.restaurants.android.restaurant_list

import com.ag_apps.restaurants.domain.models.Restaurant

/**
 * @author Ahmed Guedmioui
 */
data class RestaurantState(
    val restaurants: List<Restaurant> = emptyList(),
    val searchQuery: String = "",
    val isLocationPermissionGranted: Boolean = false
)
