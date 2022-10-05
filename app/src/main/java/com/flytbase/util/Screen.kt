package com.flytbase.util

sealed class Screen(val route: String) {

    object LoginScreen : Screen("login_screen")

    object CalculatorScreen : Screen("calculator_screen")
}
