package com.zzz1zzz.todolist_cmp

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState

@Composable
fun MainWindow(applicationScope: ApplicationScope) {
    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "Todolist CMP",
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = 1080.dp,
            height = 800.dp,
        )
    ) {
        App()
    }
}
