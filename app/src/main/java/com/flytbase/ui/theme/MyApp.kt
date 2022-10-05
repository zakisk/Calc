package com.flytbase.ui.theme

import androidx.compose.runtime.Composable


@Composable
fun MyApp(content: @Composable () -> Unit) {
    FlytBaseTheme {
        content()
    }
}