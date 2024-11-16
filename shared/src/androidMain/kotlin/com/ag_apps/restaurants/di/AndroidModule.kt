package com.ag_apps.restaurants.di

import com.ag_apps.restaurants.data.location.LocationService
import com.ag_apps.restaurants.data.restaurant.LocalRestaurantDataSource
import com.ag_apps.restaurants.domain.RestaurantDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Ahmed Guedmioui
 */

val androidModule = module {
    single { LocationService(androidContext()) }
    single<RestaurantDataSource> { LocalRestaurantDataSource(get()) }
}