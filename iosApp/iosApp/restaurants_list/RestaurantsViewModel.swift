//
//  RestaurantsViewModel.swift
//  iosApp
//
//  Created by Ahmed Guedmioui on 14/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import CoreLocation
import shared


@MainActor class RestaurantsViewModel: NSObject, ObservableObject, CLLocationManagerDelegate  {
    
    private var getRestaurantsUseCase: GetRestaurantsUseCase? = nil
    
    private let locationManager = CLLocationManager()
    @Published var authorisationStatus: CLAuthorizationStatus = .notDetermined
    
    
    @Published private(set) var restaurants = [Restaurant]()
    @Published private(set) var isLocationPermissionGranted = false
    @Published var searchQuery = "" {
        didSet {
            loadRestaurants()
        }
    }
    
    init(getRestaurantsUseCase: GetRestaurantsUseCase? = nil) {
        super.init()
        self.getRestaurantsUseCase = getRestaurantsUseCase
        locationManager.delegate = self
    }
    
    func setGetRestaurantsUseCase(getRestaurantsUseCase: GetRestaurantsUseCase) {
        self.getRestaurantsUseCase = getRestaurantsUseCase
        loadRestaurants()
    }
    
    func loadRestaurants() {
        Task() {
            do {
                restaurants = try await getRestaurantsUseCase?.invoke(
                    query: searchQuery, isLocationPermissionGranted: isLocationPermissionGranted
                ) ?? []
            } catch {
                print(error)
            }
        }
    }
    
}
