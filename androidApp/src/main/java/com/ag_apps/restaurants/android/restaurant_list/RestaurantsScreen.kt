package com.ag_apps.restaurants.android.restaurant_list

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ag_apps.restaurants.android.core.MyApplicationTheme
import com.ag_apps.restaurants.domain.models.Restaurant
import org.koin.androidx.compose.koinViewModel

/**
 * @author Ahmed Guedmioui
 */

@Composable
fun RestaurantScreenCore(
    viewModel: RestaurantViewModel = koinViewModel()
) {

    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.onAction(RestaurantAction.LoadRestaurants(isGranted))
        }
    )

    LaunchedEffect(recordAudioLauncher) {
        recordAudioLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    RestaurantScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun RestaurantScreen(
    state: RestaurantState,
    onAction: (RestaurantAction) -> Unit
) {

    Scaffold(
        topBar = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.searchQuery,
                onValueChange = {
                    onAction(RestaurantAction.UpdateSearchQuery(it))
                },
                label = {
                    Text("Search Restaurants")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.restaurants.size) { index ->
                val restaurant = state.restaurants[index]
                RestaurantItem(restaurant = restaurant)
            }
        }

    }
}

@Composable
fun RestaurantItem(
    modifier: Modifier = Modifier,
    restaurant: Restaurant
) {
    Row(
        modifier = modifier
            .height(150.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(0.5f))
    ) {
        AsyncImage(
            model = restaurant.image,
            modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.inversePrimary),
            contentScale = ContentScale.Crop,
            contentDescription = restaurant.name
        )


        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = restaurant.name,
                fontWeight = FontWeight.SemiBold
            )


            Text(
                text = restaurant.description
            )

            Text(
                text = restaurant.location,
                fontSize = 14.sp
            )

        }
    }
}

@Preview
@Composable
private fun RestaurantScreenCoreScreenPreview() {
    MyApplicationTheme {
        RestaurantScreen(
            state = RestaurantState(
                restaurants = listOf(
                    Restaurant(
                        name = "Restaurant 1",
                        description = "This is a restaurant description 1",
                        image = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2a/93/bc/9b/le-grill-robuchon-restaurant.jpg?w=600&h=-1&s=1",
                        location = "123 Main St, Anytown USA",
                        latitude = 1.0,
                        longitude = 1.0
                    ),
                    Restaurant(
                        name = "Restaurant 2",
                        description = "This is a restaurant description 2",
                        image = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2a/ba/94/13/lov-l-amour-est-la-musique.jpg?w=1200&h=-1&s=1",
                        location = "123 Main St, Anytown USA",
                        latitude = 2.0,
                        longitude = 2.0
                    ),
                    Restaurant(
                        name = "Restaurant 3",
                        description = "This is a restaurant description 3",
                        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxsoFMvMxU82lwoVgUaIZ60hUqJt1l9uWDA-2QlIJBgpQ6YsRXJDojXEuU0KYrlEgM9sc&usqp=CAU",
                        location = "123 Main St, Anytown USA",
                        latitude = 3.0,
                        longitude = 3.0
                    )
                )
            ),
            onAction = {}
        )
    }
}