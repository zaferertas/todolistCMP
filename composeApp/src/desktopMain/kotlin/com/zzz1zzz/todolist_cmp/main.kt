package com.zzz1zzz.todolist_cmp

import androidx.compose.ui.window.application
import com.zzz1zzz.todolist_cmp.di.initKoin
import org.koin.core.Koin

lateinit var koin: Koin

fun main() {
    koin = initKoin().koin

    return application {
        MainWindow(applicationScope = this)
    }
}