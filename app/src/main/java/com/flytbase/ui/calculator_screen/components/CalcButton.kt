package com.flytbase.ui.calculator_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.flytbase.ui.theme.LightOrange


@Composable
fun CalcButton(
    text: String,
    onClick: () -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.headlineMedium
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(color = Color.LightOrange.copy(alpha = .3f), shape = RectangleShape)
            .border(width = .3.dp, color = Color.LightGray, shape = RectangleShape)
            .clip(RectangleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle.copy(
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}