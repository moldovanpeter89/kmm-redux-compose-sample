import SwiftUI

struct HomeView: View {
    
    @EnvironmentObject var store: AppStore
    
    let email: String
    
    var body: some View {
        VStack {
            Text("Hello, \(email)")
            Button {
                withAnimation {
                    store.dispatch(.logout)
                }
            } label: {
                Text("Logout")
                    .foregroundColor(.white)
                    .padding([.top, .bottom], 12)
                    .padding(.horizontal, 32)
                    .background {
                        Capsule()
                            .foregroundColor(.blue)
                    }
            }

        }
    }
}
