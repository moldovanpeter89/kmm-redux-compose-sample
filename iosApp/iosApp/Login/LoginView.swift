import SwiftUI

struct LoginView: View {
    
    @EnvironmentObject var store: AppStore
    
    @State private var email: String = ""
    @State private var password: String = ""
    
    var body: some View {
        VStack {
            emailTextField
            passwordTextField
            loginButton
        }
        .padding(.horizontal, 24)
    }
    
    
    private var emailTextField: some View {
        TextField("Email", text: $email)
            .textFieldStyle(.roundedBorder)
    }
    
    private var passwordTextField: some View {
        TextField("Password", text: $password)
            .textFieldStyle(.roundedBorder)
    }
    
    private var loginButton: some View {
        Button {
            withAnimation {
                store.dispatch(.login)
            }
        } label: {
            Text("Login")
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
