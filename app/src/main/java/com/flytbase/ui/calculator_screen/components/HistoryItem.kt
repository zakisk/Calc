package com.flytbase.ui.calculator_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.flytbase.domain.models.History
import com.flytbase.ui.theme.LocalSpacing


@Composable
fun HistoryItem(history: History, onClickHistory: () -> Unit) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .padding(spacing.small)
            .fillMaxWidth()
            .clickable(onClick = onClickHistory),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = history.expression,
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = spacing.small, vertical = spacing.extraSmall)
        )
        Text(
            text = history.result,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = spacing.small, vertical = spacing.extraSmall)
        )
    }
}