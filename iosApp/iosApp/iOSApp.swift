import SwiftUI
import shared

@main
struct iOSApp: App {
    
    private let iosModule = IosModule()
    
	var body: some Scene {
		WindowGroup {
            RestaurantsScreen(getRestaurantsUseCase: iosModule.getRestaurantsUseCase)
		}
	}
}
