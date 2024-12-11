package com.numpol.cmptodo

import androidx.compose.ui.window.ComposeUIViewController
import com.numpol.cmptodo.app.App
import com.numpol.cmptodo.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }