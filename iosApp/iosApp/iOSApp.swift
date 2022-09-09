import SwiftUI

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            ContentView()
                .environmentObject(AppStore(AppState(), reducer: reducer))
		}
	}
}
