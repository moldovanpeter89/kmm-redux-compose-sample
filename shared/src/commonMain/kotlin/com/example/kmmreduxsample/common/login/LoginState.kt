package com.example.kmmreduxsample.common.login

import com.example.kmmreduxsample.core.redux.Action
import com.example.kmmreduxsample.core.redux.Effect
import com.example.kmmreduxsample.core.redux.GeneralState

data class LoginState(
    val progress: Boolean,
    val error: String?) : GeneralState {

    companion object {
        fun getInitialDefault(): LoginState {
            return LoginState(false, null)
        }
    }
}

sealed class LoginAction : Action {
    data class SignIn(val username: String, val password: String) : LoginAction()
    object SignOut : LoginAction()
    object SignedOut : LoginAction()
    data class Error(val error: String) : LoginAction()
}

object SignedInSideEffect : Effect
object SignedOutSideEffect : Effect