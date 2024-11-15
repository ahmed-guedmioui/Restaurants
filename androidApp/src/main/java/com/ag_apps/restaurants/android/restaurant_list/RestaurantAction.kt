package com.ag_apps.restaurants.android.restaurant_list

/**
 * @author Ahmed Guedmioui
 */
sealed interface RestaurantAction {
    data class LoadRestaurants(val isLocationPermissionGranted: Boolean) : RestaurantAction
    data class UpdateSearchQuery(val query: String) : RestaurantAction
}