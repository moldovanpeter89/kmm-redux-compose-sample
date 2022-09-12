package com.example.kmmreduxsample

import com.example.kmmreduxsample.common.app.AppState
import com.example.kmmreduxsample.core.redux.Action
import com.example.kmmreduxsample.core.redux.Effect
import com.example.kmmreduxsample.core.redux.Store
import org.koin.dsl.koinApplication
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun getObservableStore() = koinApplication {
    koin.get<Store<AppState, Action, Effect>>()
}