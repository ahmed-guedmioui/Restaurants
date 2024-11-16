package com.ag_apps.restaurants.data.location

import com.ag_apps.restaurants.domain.models.Location

/**
 * @author Ahmed Guedmioui
 */
expect class LocationService {
    suspend fun getCurrentLocation(): Location?
}