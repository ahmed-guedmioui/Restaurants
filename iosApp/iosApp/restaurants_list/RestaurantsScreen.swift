//
//  RestaurantsScreen.swift
//  iosApp
//
//  Created by Ahmed Guedmioui on 14/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RestaurantsScreen: View {
    
    @ObservedObject var viewModel: RestaurantsViewModel
    
    init() {
        self.viewModel = RestaurantsViewModel(getRestaurantsUseCase: getRestaurantsUseCase)
    }
    
    var body: some View {
        
        VStack {
            TextField("Search for restaurants...", text: $viewModel.searchQuery)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding()
                .frame(maxWidth: .infinity, minHeight: 50)
            
            List {
                ForEach(viewModel.restaurants, id: \.self.name) { restaurant in
                    NoteItem(restaurant: restaurant)
                }
            }
            .listStyle(.plain)
            .listRowSeparator(.hidden)
        }
    }
}

struct NoteItem: View {
    var restaurant: Restaurant
    
    var body: some View {
        HStack {
            AsyncImage(
                url: URL(string: restaurant.image)
            ) { phase in
                switch phase {
                case .empty:
                    ProgressView()
                        .frame(width: 150, height: 150)
                        .foregroundColor(.gray)
                case .success(let image):
                    image
                        .scaledToFill()
                        .frame(width: 150, height: 150)
                        .clipped()
                case .failure:
                    Image(systemName: "photo")
                        .scaledToFit()
                        .frame(width: 150, height: 150)
                        .foregroundColor(.gray)
                @unknown default:
                    EmptyView()
                }
            }
            
            VStack(alignment: .leading) {
                Text(restaurant.name)
                    .font(.title3)
                    .fontWeight(.semibold)
                
                Spacer()
                
                Text(restaurant.description_)
                    .fontWeight(.light)
                
                Spacer()
                
                Text(restaurant.location)
                    .fontWeight(.light)
            }
            .padding()
           
        }
        .frame(maxWidth: .infinity, maxHeight: 150)
        .background(Color.gray.opacity(0.05))
        .clipShape(RoundedRectangle(cornerRadius: 10.0))
        .shadow(radius: 2)
    }
}

