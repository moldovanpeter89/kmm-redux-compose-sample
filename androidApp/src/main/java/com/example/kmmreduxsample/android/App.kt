package com.example.kmmreduxsample.android

import android.app.Application
import com.example.kmmreduxsample.android.common.viewmodel.StoreViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.component.KoinComponent
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        com.example.kmmreduxsample.core.di.initKoin {
            modules(
                module {
                single { StoreViewModel(get()) }
            })
        }
    }
}