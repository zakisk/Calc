package com.flytbase.ui.calculator_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flytbase.R
import com.flytbase.ui.CalculatorViewModel
import com.flytbase.util.showToast


@Composable
fun Keypad(viewModel: CalculatorViewModel) {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier.fillMaxHeight(),
        columns = GridCells.Adaptive(80.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        itemsIndexed(viewModel.buttons) { index, button ->
            CalcButton(
                text = when (index) {
                    3 -> stringResource(id = R.string.divide)
                    7 -> stringResource(id = R.string.multiply)
                    11 -> stringResource(id = R.string.plus)
                    15 -> stringResource(id = R.string.minus)
                    else -> button
                },
                onClick = {
                    if (index == 19) {
                        viewModel.calculate(context, true)
                    } else {
                        if (index == 17 && viewModel.expression.last().toString() == viewModel.divide) {
                            context.showToast("Cannot be divided by zero")
                        } else {
                            viewModel.append(index, button)
                        }
                        viewModel.calculate(context)
                    }
                },
                textStyle = if (button == "ENTER" || button == "CE" || button == "Del")
                    MaterialTheme.typography.bodyLarge
                else MaterialTheme.typography.headlineSmall
            )
        }
    }
}