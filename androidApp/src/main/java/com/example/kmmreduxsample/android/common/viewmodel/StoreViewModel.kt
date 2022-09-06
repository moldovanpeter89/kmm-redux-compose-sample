package com.example.kmmreduxsample.android.common.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kmmreduxsample.common.app.AppState
import com.example.kmmreduxsample.core.redux.Action
import com.example.kmmreduxsample.core.redux.Effect
import com.example.kmmreduxsample.core.redux.Store
import kotlin.time.ExperimentalTime

@ExperimentalTime
class StoreViewModel(
    val store: Store<AppState, Action, Effect>,
) : ViewModel() {

    fun dispatch(action: Action) {
        store.dispatch(action)
    }
}