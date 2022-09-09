import SwiftUI

typealias AppStore = Store<AppState, AppActions>

class Store<State, Action>: ObservableObject {
    @Published private(set) var state: State
    
    private let reducer: Reducer<State, Action>
    
    private let queue = DispatchQueue(
      label: "com.kmm.redux.store",
      qos: .userInitiated)
    
    init(_ initial: State, reducer: @escaping Reducer<State, Action>) {
        self.state = initial
        self.reducer = reducer
    }
    
    func dispatch(_ action: Action) {
      queue.sync {
          self.dispatch(self.state, action)
      }
    }
    
    private func dispatch(_ currentState: State, _ action: Action) {
      let newState = reducer(currentState, action)
      state = newState
    }
}
