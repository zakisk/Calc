package com.flytbase.ui.calculator_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.flytbase.R
import com.flytbase.ui.CalculatorViewModel
import com.flytbase.ui.calculator_screen.components.HistoryList
import com.flytbase.ui.calculator_screen.components.Keypad
import com.flytbase.ui.calculator_screen.components.ResultBoard
import com.flytbase.ui.calculator_screen.components.ViewHistory
import com.flytbase.ui.theme.Isabelline
import com.flytbase.ui.theme.LightOrange
import com.flytbase.ui.theme.LocalSpacing
import com.flytbase.ui.theme.Onyx

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel) {
    val spacing = LocalSpacing.current
    var showHistory by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Isabelline),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightOrange.copy(alpha = .3f))
        ) {
            Text(
                text = stringResource(id = R.string.title),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.ExtraBold
                ),
                color = Color.Onyx,
                modifier = Modifier.padding(spacing.medium)
            )
        }

        ResultBoard(
            modifier = Modifier
                .padding(
                    horizontal = spacing.medium,
                    vertical = spacing.small
                )
                .fillMaxHeight(.35f),
            expression = viewModel.expression,
            result = viewModel.result
        )

        ViewHistory(tint = if (showHistory) Color.LightOrange else Color.Gray) {
            showHistory = !showHistory
        }

        if (!showHistory) {
            Keypad(viewModel)
        } else {
            HistoryList(viewModel = viewModel)
        }
    }
}