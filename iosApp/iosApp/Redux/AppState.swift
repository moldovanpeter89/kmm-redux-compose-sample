import Foundation

struct AppState {
    var state: MyAppStates = .loggedOut
}

enum MyAppStates {
    case loggedIn
    case loggedOut
}
