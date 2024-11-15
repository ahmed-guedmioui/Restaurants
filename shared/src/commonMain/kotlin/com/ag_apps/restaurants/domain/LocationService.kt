package com.ag_apps.restaurants.domain

import com.ag_apps.restaurants.domain.models.Location

/**
 * @author Ahmed Guedmioui
 */
expect class LocationService {
    suspend fun getCurrentLocation(): Location?
}