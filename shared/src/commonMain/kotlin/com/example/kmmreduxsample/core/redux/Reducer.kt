package com.example.kmmreduxsample.core.redux

interface Reducer<T : GeneralState> {
    fun reduce(oldState: T, action: Action): T
}