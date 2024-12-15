package com.numpol.cmptodo

import android.app.Application
import com.numpol.cmptodo.di.initKoin
import com.numpol.cmptodo.mars_photo.data.AppContainer
import com.numpol.cmptodo.mars_photo.data.DefaultAppContainer
import org.koin.android.ext.koin.androidContext

class TodoApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()

        initKoin {
            androidContext(this@TodoApplication)
        }
    }
}