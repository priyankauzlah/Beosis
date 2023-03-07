package com.uzlahalya.beosis4

import android.app.Application
import com.uzlahalya.beosis4.util.ContextProvider

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ContextProvider.initialize(applicationContext)
    }
}