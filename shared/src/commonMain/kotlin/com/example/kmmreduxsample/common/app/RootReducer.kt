package com.example.kmmreduxsample.common.app

import com.example.kmmreduxsample.common.login.LoginReducer
import com.example.kmmreduxsample.core.redux.Action
import com.example.kmmreduxsample.core.redux.Reducer
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class RootReducer(
    private val loginReducer: LoginReducer,
) : Reducer<AppState> {
    override fun reduce(oldState: AppState, action: Action): AppState {
        return oldState.copy(loginState = loginReducer.reduce(oldState.loginState, action))
    }
}