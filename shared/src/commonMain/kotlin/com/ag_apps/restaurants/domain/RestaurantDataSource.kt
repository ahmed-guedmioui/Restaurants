package com.ag_apps.restaurants.domain

import com.ag_apps.restaurants.domain.models.Restaurant

/**
 * @author Ahmed Guedmioui
 */
interface RestaurantDataSource {
   suspend fun getRestaurants(isLocationPermissionGranted: Boolean): List<Restaurant>
}