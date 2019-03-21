package com.google.android.commerce

import android.app.Application
import com.google.android.commerce.injection.AppComponent
import com.google.android.commerce.injection.AppModule
import com.google.android.commerce.injection.DaggerAppComponent
import timber.log.Timber

class AppCommerce : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())

        }
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
            private set
    }
}