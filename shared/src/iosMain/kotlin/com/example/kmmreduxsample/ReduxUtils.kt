package com.example.kmmreduxsample

import com.example.kmmreduxsample.core.redux.ReduxStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)
fun ReduxStore.watchState() = observeState().wrap()

@OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)
fun ReduxStore.watchSideEffect() = observeSideEffect().wrap()