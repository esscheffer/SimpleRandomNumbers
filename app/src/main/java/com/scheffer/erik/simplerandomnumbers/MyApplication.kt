package com.scheffer.erik.simplerandomnumbers

import android.app.Application
import com.scheffer.erik.simplerandomnumbers.koin.networkModule
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin { modules(networkModule) }
    }
}