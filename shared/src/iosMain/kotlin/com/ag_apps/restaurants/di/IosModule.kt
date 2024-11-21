package com.ag_apps.restaurants.di

import com.ag_apps.restaurants.data.location.LocationService
import com.ag_apps.restaurants.data.restaurant.LocalRestaurantDataSource
import com.ag_apps.restaurants.domain.RestaurantDataSource
import com.ag_apps.restaurants.domain.usecase.GetRestaurantsUseCase

/**
 * @author Ahmed Guedmioui
 */
class IosModule {
    private val locationService by lazy { LocationService() }

    private val restaurantDataSource: RestaurantDataSource by lazy {
        LocalRestaurantDataSource(locationService)
    }

    val getRestaurantsUseCase: GetRestaurantsUseCase by lazy {
        GetRestaurantsUseCase(restaurantDataSource)
    }
}