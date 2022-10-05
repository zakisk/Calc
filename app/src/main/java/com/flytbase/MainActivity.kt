package com.flytbase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.flytbase.ui.CalculatorViewModel
import com.flytbase.ui.FlytNavHost

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        symbols()
        setContent {
            MainScreen()
        }
    }

    private fun symbols() {
        viewModel.multiply = getString(R.string.multiply)
        viewModel.divide = getString(R.string.divide)
        viewModel.plus = getString(R.string.plus)
        viewModel.minus = getString(R.string.minus)
    }

    @Composable
    fun MainScreen() {
        FlytNavHost(viewModel = viewModel)
    }
}