package com.ag_apps.restaurants.domain.usecase

import com.ag_apps.restaurants.domain.models.Restaurant
import com.ag_apps.restaurants.domain.RestaurantDataSource

/**
 * @author Ahmed Guedmioui
 */
class GetRestaurantsUseCase(
    private val restaurantDataSource: RestaurantDataSource
) {
   suspend operator fun invoke(
        query: String,
        isLocationPermissionGranted: Boolean
    ): List<Restaurant> {

        val restaurants = restaurantDataSource.getRestaurants(
            isLocationPermissionGranted
        )

        if (query.isBlank()) {
            return restaurants
        }

        return restaurants
            .filter {
                it.name.contains(query, ignoreCase = true)
                        || it.description.contains(query, ignoreCase = true)
            }
    }
}