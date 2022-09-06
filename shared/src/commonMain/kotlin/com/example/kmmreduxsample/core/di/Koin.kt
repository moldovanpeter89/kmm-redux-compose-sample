package com.example.kmmreduxsample.core.di

import com.example.kmmreduxsample.common.app.AppState
import com.example.kmmreduxsample.common.app.RootReducer
import com.example.kmmreduxsample.common.login.LoginMiddleware
import com.example.kmmreduxsample.common.login.LoginReducer
import com.example.kmmreduxsample.core.redux.Action
import com.example.kmmreduxsample.core.redux.Effect
import com.example.kmmreduxsample.core.redux.ReduxStore
import com.example.kmmreduxsample.core.redux.Store
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule)
    modules(storeModule)
}

@ExperimentalCoroutinesApi
@ExperimentalTime
val storeModule = module {
    single<Store<AppState, Action, Effect>> {
        ReduxStore(
            reducer = RootReducer(
                loginReducer = get(),
            ),
            defaultValue = AppState(),
            middlewares = listOf(
                LoginMiddleware(),
            )
        )
    }

    single { LoginReducer() }
}

val commonModule = module {
}
