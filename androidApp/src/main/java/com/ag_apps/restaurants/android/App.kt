package com.ag_apps.restaurants.android

import android.app.Application
import com.ag_apps.restaurants.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author Ahmed Guedmioui
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                androidModule
            )
        }
    }

}