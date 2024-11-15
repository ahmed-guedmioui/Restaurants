package com.ag_apps.restaurants.android.restaurant_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag_apps.restaurants.domain.usecase.GetRestaurantsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Ahmed Guedmioui
 */
class RestaurantViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase
) : ViewModel() {

    var state by mutableStateOf(RestaurantState())
        private set

    private var searchJob: Job? = null

    fun onAction(action: RestaurantAction) {
        when (action) {
            is RestaurantAction.LoadRestaurants -> {
                state = state.copy(
                    isLocationPermissionGranted = action.isLocationPermissionGranted
                )
                getRestaurants()
            }

            is RestaurantAction.UpdateSearchQuery -> {
                state = state.copy(searchQuery = action.query)
                searchRestaurants()
            }
        }
    }

    private fun getRestaurants() {
        viewModelScope.launch {
            state = state.copy(
                restaurants = getRestaurantsUseCase(
                    query = state.searchQuery,
                    isLocationPermissionGranted = state.isLocationPermissionGranted
                )
            )
        }
    }

    private fun searchRestaurants() {
        searchJob = null
        searchJob = viewModelScope.launch {
            delay(500)
            state = state.copy(
                restaurants = getRestaurantsUseCase(
                    query = state.searchQuery,
                    isLocationPermissionGranted = state.isLocationPermissionGranted
                )
            )
        }
    }
}