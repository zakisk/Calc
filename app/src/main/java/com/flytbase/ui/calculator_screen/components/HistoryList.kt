package com.flytbase.ui.calculator_screen.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flytbase.ui.CalculatorViewModel


@Composable
fun HistoryList(viewModel: CalculatorViewModel) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(viewModel.allHistory) { history ->
            HistoryItem(
                history = history,
                onClickHistory = {
                    viewModel.expression = history.expression
                    viewModel.result = history.result
                }
            )
        }
    }
}