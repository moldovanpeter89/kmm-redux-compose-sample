import Foundation

typealias Reducer<State, Action> = (State, Action) -> State

let reducer: Reducer<AppState, AppActions> = { state, action in
    var mutatingState = state
    switch action {
    case .login:
        mutatingState.state = .loggedIn
    case .logout:
        mutatingState.state = .loggedOut
    }

    return mutatingState
}
