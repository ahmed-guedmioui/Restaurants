package com.ag_apps.restaurants.data

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