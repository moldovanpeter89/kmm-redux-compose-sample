package com.example.kmmreduxsample.common.app

import com.example.kmmreduxsample.common.login.LoginState
import com.example.kmmreduxsample.core.redux.Action
import com.example.kmmreduxsample.core.redux.GeneralState
import kotlin.time.ExperimentalTime

@ExperimentalTime
data class AppState(
    internal val loginState: LoginState = LoginState.getInitialDefault(),
) : GeneralState {
    fun getLoginState() = loginState
}