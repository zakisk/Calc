package com.flytbase.ui.calculator_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.flytbase.R
import com.flytbase.ui.theme.LocalSpacing


@Composable
fun ViewHistory(tint: Color, onClick: () -> Unit) {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .padding(horizontal = spacing.medium, vertical = spacing.small)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = MutableInteractionSource()
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_history),
            contentDescription = "History",
            tint = tint
        )

        Text(
            text = stringResource(id = R.string.view_history),
            style = MaterialTheme.typography.bodySmall,
            color = tint,
            modifier = Modifier.padding(start = spacing.small)
        )
    }
}