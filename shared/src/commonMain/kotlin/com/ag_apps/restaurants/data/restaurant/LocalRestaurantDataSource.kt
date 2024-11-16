package com.ag_apps.restaurants.data.restaurant

import com.ag_apps.restaurants.data.location.LocationService
import com.ag_apps.restaurants.domain.models.Restaurant
import com.ag_apps.restaurants.domain.RestaurantDataSource
import com.ag_apps.restaurants.domain.usecase.isRestaurantWithin3km

/**
 * @author Ahmed Guedmioui
 */
class LocalRestaurantDataSource(
    private val locationService: LocationService
) : RestaurantDataSource {

    override suspend fun getRestaurants(isLocationPermissionGranted: Boolean): List<Restaurant> {

        val location = locationService.getCurrentLocation()

        if (location != null) {
            return dummyRestaurants
                .map { it.toRestaurant() }
                .filter { restaurant ->
                    restaurant.isRestaurantWithin3km(
                        userLatitude = location.latitude, userLongitude = location.longitude
                    )
                }
        }

        return dummyRestaurants.map { it.toRestaurant() }
    }

    private val dummyRestaurants = listOf(
        RestaurantDto(
            name = "Restaurant 1",
            description = "This is a restaurant description 1",
            image = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2a/93/bc/9b/le-grill-robuchon-restaurant.jpg?w=600&h=-1&s=1",
            location = "1 Main St, Anytown USA",
            latitude = 30.400312,
            longitude = -9.548005
        ),
        RestaurantDto(
            name = "Restaurant 2",
            description = "This is a restaurant description 2",
            image = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2a/ba/94/13/lov-l-amour-est-la-musique.jpg?w=1200&h=-1&s=1",
            location = "2 Main St, Anytown USA",
            latitude = 37.5075,
            longitude = -122.0489
        ),
        RestaurantDto(
            name = "Restaurant 3",
            description = "This is a restaurant description 3",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxsoFMvMxU82lwoVgUaIZ60hUqJt1l9uWDA-2QlIJBgpQ6YsRXJDojXEuU0KYrlEgM9sc&usqp=CAU",
            location = "3 Main St, Anytown USA",
            latitude = 37.4562,
            longitude = -122.0800
        ),
        RestaurantDto(
            name = "Restaurant 4",
            description = "This is a restaurant description 4",
            image = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/af/c4/6a/restaurant-o-playa.jpg?w=600&h=400&s=1",
            location = "4 Main St, Anytown USA",
            latitude = 37.4928,
            longitude = -122.0839
        ),
        RestaurantDto(
            name = "Restaurant 5",
            description = "This is a restaurant description 5",
            image = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2b/02/14/5e/restaurant-aglio-e-olio.jpg?w=600&h=-1&s=1",
            location = "5 Main St, Anytown USA",
            latitude =30.400965,
            longitude = -9.546140
        ),
        RestaurantDto(
            name = "Restaurant 6",
            description = "This is a restaurant description 6",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQex4BuZoCDm5imKkXQ7Hh1anIT0MNFb6g3tw&s",
            location = "6 Main St, Anytown USA",
            latitude = 37.4571,
            longitude = -122.0609
        ),
        RestaurantDto(
            name = "Restaurant 7",
            description = "This is a restaurant description 7",
            image = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/21/7d/bb/5a/restaurant-spicy-bamboo.jpg?w=600&h=400&s=1",
            location = "7 Main St, Anytown USA",
            latitude = 37.4875,
            longitude = -122.1369
        ),
        RestaurantDto(
            name = "Restaurant 8",
            description = "This is a restaurant description 8",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQkj07AztXIp3Mnd6Dtfw75coJamQh4PQRwg&s",
            location = "8 Main St, Anytown USA",
            latitude = 37.4751,
            longitude = -122.0571
        ),
        RestaurantDto(
            name = "Restaurant 9",
            description = "This is a restaurant description 9",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcFobgiqwzm0EAgDz0zSWL2OhUhNUAsMztfQ&s",
            location = "9 Main St, Anytown USA",
            latitude = 37.4322,
            longitude = -122.0802
        ),
        RestaurantDto(
            name = "Restaurant 10",
            description = "This is a restaurant description 10",
            image = "https://www.valdoise-tourisme.com/wp-content/uploads/external/cambrousse-restaurant-13-e1681486278554-570x447.jpg",
            location = "10 Main St, Anytown USA",
            latitude = 30.400061,
            longitude = -9.548539
        ),
        RestaurantDto(
            name = "Restaurant 11",
            description = "This is a restaurant description 11",
            image = "https://static.wixstatic.com/media/19b35c_0ebd1b5204934384a00f5c0540103a91.jpg",
            location = "11 Main St, Anytown USA",
            latitude = 37.5123,
            longitude = -122.1056
        ),
        RestaurantDto(
            name = "Restaurant 12",
            description = "This is a restaurant description 12",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_aUlh-D-xxMYlDCm6jOC9ODKnqbbba9oqrQ&s",
            location = "12 Main St, Anytown USA",
            latitude = 37.4801,
            longitude = -122.1302
        ),
        RestaurantDto(
            name = "Restaurant 13",
            description = "This is a restaurant description 13",
            image = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2a/af/5b/87/jack-london-brewing-co.jpg?w=600&h=-1&s=1",
            location = "13 Main St, Anytown USA",
            latitude = 37.4963,
            longitude = -122.1423
        ),
        RestaurantDto(
            name = "Restaurant 14",
            description = "This is a restaurant description 14",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQF9msOe1TV04h8i5Ch9LhRPlTsiLMRdv1VZV2JbmBvqTXbjoNj2ElN0sjOp8d2lVmlD8-4&s",
            location = "14 Main St, Anytown USA",
            latitude = 37.4793,
            longitude = -122.1557
        ),
        RestaurantDto(
            name = "Restaurant 15",
            description = "This is a restaurant description 15",
            image = "https://www.mohammadkhadim.com/wp-content/uploads/2023/06/17.jpg",
            location = "15 Main St, Anytown USA",
            latitude = 30.401215,
            longitude = -9.547881
        )
    )


}