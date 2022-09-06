package com.example.kmmreduxsample.common.login

import com.example.kmmreduxsample.common.app.AppState
import com.example.kmmreduxsample.core.redux.Action
import com.example.kmmreduxsample.core.redux.Effect
import com.example.kmmreduxsample.core.redux.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlin.time.ExperimentalTime


@ExperimentalTime
class LoginMiddleware constructor(
//    private val repo: AuthRepo,
) : Middleware<AppState> {

    override suspend fun process(state: AppState, action: Action, sideEffect: MutableSharedFlow<Effect>): Flow<Action> = flow {
        run {
            when (action) {
                is LoginAction.SignIn -> {
                    // BL repo.login....
                    // respo d
                    if (action.username != "error") {
                        sideEffect.emit(SignedInSideEffect)
                    } else {
                        emit(LoginAction.Error("mak"))
                    }
                }
                is LoginAction.SignOut -> {
                    // repo.signout
                    emit(LoginAction.SignedOut)
                    sideEffect.emit(SignedOutSideEffect)
                }
                else -> emptyFlow<Action>()
            }
        }
    }
}