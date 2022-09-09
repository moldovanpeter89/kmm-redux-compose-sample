import SwiftUI

struct ContentView: View {
    
    @EnvironmentObject var store: AppStore
    
    var body: some View {
        switch store.state.state {
        case .loggedIn:
            HomeView(email: "")
        case .loggedOut:
            LoginView()
        }
    }
}
