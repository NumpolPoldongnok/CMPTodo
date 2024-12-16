package com.numpol.cmptodo

import android.app.Application
import com.numpol.cmptodo.di.initKoin
import org.koin.android.ext.koin.androidContext

class TodoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@TodoApplication)
        }
    }
}