package com.flytbase.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flytbase.ui.calculator_screen.CalculatorScreen
import com.flytbase.ui.login_screen.LoginScreen
import com.flytbase.util.Screen


@Composable
fun FlytNavHost(viewModel: CalculatorViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.CalculatorScreen.route) {
            CalculatorScreen(viewModel = viewModel)
        }
    }
}