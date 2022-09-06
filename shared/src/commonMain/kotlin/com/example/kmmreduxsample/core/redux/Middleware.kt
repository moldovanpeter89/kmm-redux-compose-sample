package com.example.kmmreduxsample.core.redux

import kotlin.time.ExperimentalTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface Middleware<T : GeneralState> {
    @ExperimentalTime
    suspend fun process(state: T, action: Action, sideEffect: MutableSharedFlow<Effect>): Flow<Action>
}