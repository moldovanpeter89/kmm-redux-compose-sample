package com.example.kmmreduxsample.common.login

import com.example.kmmreduxsample.core.redux.Action
import com.example.kmmreduxsample.core.redux.Reducer

class LoginReducer : Reducer<LoginState> {

    override fun reduce(oldState: LoginState, action: Action): LoginState {
        return when (action) {
            is LoginAction.SignIn -> {
                if (oldState.progress) {
                    oldState
                } else {
                    oldState.copy(progress = true, error = null)
                }
            }
            is LoginAction.Error -> {
                if (oldState.progress) {
                    oldState.copy(
                        progress = false,
                        error = action.error
                    )
                } else {
                    oldState
                }
            }
            is LoginAction.SignOut -> {
                if (oldState.progress) {
                    oldState
                } else {
                    oldState.copy(progress = true, error = null)
                }
            }
            is LoginAction.SignedOut -> {
                LoginState.getInitialDefault()
            }
            else -> oldState
        }
    }
}