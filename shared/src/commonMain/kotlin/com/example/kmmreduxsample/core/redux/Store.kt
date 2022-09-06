package com.example.kmmreduxsample.core.redux

import com.example.kmmreduxsample.common.app.AppState
import com.example.kmmreduxsample.common.app.RootReducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

interface GeneralState
interface Action
interface Effect
object None : Action
object Empty: Effect

interface Store<S : GeneralState, A : Action, E : Effect> {
    fun observeState(): StateFlow<S>
    fun dispatch(action: A)
    fun getState(): S
    fun observeSideEffect(): Flow<E>
}

@ExperimentalCoroutinesApi
@ExperimentalTime
open class ReduxStore(
    private val reducer: RootReducer,
    defaultValue: AppState,
    private val middlewares: List<Middleware<AppState>>
) : Store<AppState, Action, Effect>, CoroutineScope by CoroutineScope(Dispatchers.Default.limitedParallelism(1)) {

    private val state = MutableStateFlow(defaultValue)
    private val sideEffect = MutableSharedFlow<Effect>()

    override fun observeState(): StateFlow<AppState> = state.asStateFlow()

    override fun dispatch(action: Action) {
        launch {
            dispatchSync(action)
        }
    }

    private fun dispatchSync(action: Action) {
        val oldState = state.value
        val newState = reducer.reduce(oldState, action)

        middlewares.forEach { middleware ->
            launch {
                middleware.process(newState, action, sideEffect).collect { middleWareAction ->
                    dispatch(middleWareAction)
                }
            }
        }

        if (newState != oldState) {
            state.value = newState
        }
    }

    override fun getState(): AppState {
        return state.value
    }

    override fun observeSideEffect(): Flow<Effect> = sideEffect
}

@ExperimentalTime
inline fun <reified T : GeneralState> Reducer<T>.getState(
    appState: AppState,
    mapState: (AppState) -> T
): T = mapState(appState)