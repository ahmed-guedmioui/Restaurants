package com.ag_apps.restaurants.data.restaurant

import com.ag_apps.restaurants.domain.models.Restaurant

/**
 * @author Ahmed Guedmioui
 */

fun RestaurantDto.toRestaurant() = Restaurant(
    name = name,
    description = description,
    image = image,
    location = location,
    latitude = latitude,
    longitude = longitude
)