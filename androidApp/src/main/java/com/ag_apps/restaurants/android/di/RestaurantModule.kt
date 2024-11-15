package com.ag_apps.restaurants.android.di

import com.ag_apps.restaurants.android.restaurant_list.RestaurantViewModel
import com.ag_apps.restaurants.domain.usecase.GetRestaurantsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Ahmed Guedmioui
 */

val restaurantModule = module {
    single { GetRestaurantsUseCase(get()) }
    viewModel { RestaurantViewModel(get()) }
}